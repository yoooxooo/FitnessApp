package app.it_academy.fitnessAppUsers.service;


import app.it_academy.fitnessAppUsers.core.Audit.annotations.Audited;
import app.it_academy.fitnessAppUsers.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.FullUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.RegisterByAdminUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.UpdateUserDto;
import app.it_academy.fitnessAppUsers.core.exceptions.ErrorObject;
import app.it_academy.fitnessAppUsers.core.exceptions.custom.FieldValidationException;
import app.it_academy.fitnessAppUsers.dao.UserRepository;
import app.it_academy.fitnessAppUsers.domain.User;
import app.it_academy.fitnessAppUsers.mappers.PageMapper;
import app.it_academy.fitnessAppUsers.mappers.UserMapper;
import app.it_academy.fitnessAppUsers.service.api.IUserService;
import jakarta.persistence.OptimisticLockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserRepository dao;

    private final UserMapper userMapper;

    private final PageMapper<FullUserDto> pageMapper;

    public UserService(UserRepository dao, UserMapper userMapper, PageMapper<FullUserDto> pageMapper) {
        this.dao = dao;
        this.userMapper = userMapper;
        this.pageMapper = pageMapper;
    }

    @Override
    public PageDto<FullUserDto> getAllUsers(Integer pageNumber, Integer pageSize) {
        if (pageNumber < 0 || pageSize < 1) {
            throw new IllegalArgumentException("Страницы с такими параметрами не существует");
        }
        Page<User> userPage;
        if ((userPage = dao.findAll(PageRequest.of(pageNumber, pageSize))).getTotalPages() < pageNumber + 1) {
            throw new IllegalArgumentException("Общее количество страниц меньше чем номер запрашиваемой");
        }
        return pageMapper.toDto(userPage.map(userMapper::createDto));
    }

    @Override
    public FullUserDto getSingleUser(UUID uuid) {
        User user = dao.findById(uuid).orElseThrow();
            return userMapper.createDto(user);
    }

    @Override
    @Audited(operationType = "CREATION")
    public UUID createUser(RegisterByAdminUserDto userDto) {
        List<ErrorObject> errors;
        if ((errors = userDto.checkFields()).isEmpty()) {
            User user = userMapper.createEntity(userDto);
            return dao.save(user).getId();
        } else {
            throw new FieldValidationException("Validation Error", errors);
        }
    }

    @Override
    @Audited(operationType = "UPDATE")
    public UUID updateUser(UUID uuid, Instant updateLastTime, UpdateUserDto userDto) {
        User bufferedUser = dao.findById(uuid).orElseThrow();
        if(bufferedUser.getMail().equals("SYSTEM@SYSTEM.SYSTEM")) {
            throw new OptimisticLockException("Системного пользователя нельзя изменять");
        }
        Long checkUpdateTimeLong = bufferedUser.getUpdateDate().getEpochSecond();
        Long lastUpdateTimeLong = updateLastTime.getEpochSecond();
        if (lastUpdateTimeLong.equals(checkUpdateTimeLong)) {
            User user = userMapper.updateUser(userDto.combine(bufferedUser), bufferedUser);
            return dao.save(user).getId();
        } else throw new OptimisticLockException("Сущность уже была изменена");
    }
}
