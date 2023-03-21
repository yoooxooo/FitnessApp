package app.it_academy.fitnessAppUsers.service;

import app.it_academy.fitnessAppUsers.core.Audit.annotations.Audited;
import app.it_academy.fitnessAppUsers.core.dto.userDto.FullUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.LoginUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.RegisterUserDto;
import app.it_academy.fitnessAppUsers.core.exceptions.ErrorObject;
import app.it_academy.fitnessAppUsers.core.exceptions.custom.DeniedAccessException;
import app.it_academy.fitnessAppUsers.core.exceptions.custom.FieldValidationException;
import app.it_academy.fitnessAppUsers.dao.LoginRepository;
import app.it_academy.fitnessAppUsers.domain.User;
import app.it_academy.fitnessAppUsers.domain.UserStatus;
import app.it_academy.fitnessAppUsers.feign.MailFeignClient;
import app.it_academy.fitnessAppUsers.mappers.UserMapper;
import app.it_academy.fitnessAppUsers.service.api.ILoginService;
import app.it_academy.fitnessAppUsers.service.api.IUserService;
import app.it_academy.fitnessAppUsers.utils.JwtTokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoginService implements ILoginService {

    private final LoginRepository dao;

    private final IUserService userService;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    private final UserHolder userHolder;
    private final MailFeignClient mailFeignClient;

    private final JwtTokenUtil jwtTokenUtil;

    public LoginService(LoginRepository dao, IUserService userService, UserMapper userMapper,
                        PasswordEncoder encoder, UserHolder userHolder, MailFeignClient mailFeignClient, JwtTokenUtil jwtTokenUtil) {
        this.dao = dao;
        this.userService = userService;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.userHolder = userHolder;
        this.mailFeignClient = mailFeignClient;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @Override
    @Audited(operationType = "CREATION")
    public UUID register(RegisterUserDto userDto) {
        List<ErrorObject> errors;
        if ((errors = userDto.checkFields()).isEmpty()) {
            User user = dao.save(userMapper.createEntity(userDto));
            mailFeignClient.sendSecret(user.getMail(), user.getId());
            return user.getId();
        } else {
            throw new FieldValidationException("Validation Error", errors);
        }
    }

    @Override
    @Audited(operationType = "UPDATE")
    public UUID verify(String key, String mail) {
        User user = dao.findByMail(mail).orElseThrow();
        StringBuilder str = new StringBuilder(user.getId().toString());
        if ((str.reverse().toString()).equals(key)) {
            user.setStatus(UserStatus.ACTIVATED);
            return dao.save(user).getId();
        } else throw new IllegalArgumentException("Секретный ключ верификации неправильный");
    }

    @Override
    public String login(LoginUserDto loginDto) {
        List<ErrorObject> errors;
        if ((errors = loginDto.checkFields()).isEmpty()) {
            User user = dao.findByMail(loginDto.getMail()).orElseThrow();
            if (user.getStatus() == UserStatus.ACTIVATED) {
                if (encoder.matches(loginDto.getPassword(), user.getPassword())) {
                    return jwtTokenUtil.generateAccessToken(user);
                } else throw new IllegalArgumentException("Пароль неверный");
            } else throw new DeniedAccessException("Данный аккаунт не активирован");
        } throw new FieldValidationException("Validation Error", errors);
    }

    @Override
    public FullUserDto getCurrentUser() {
        return userService.getSingleUser(userHolder.getUser().getId());
    }


}
