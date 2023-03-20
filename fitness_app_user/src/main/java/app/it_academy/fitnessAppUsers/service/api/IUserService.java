package app.it_academy.fitnessAppUsers.service.api;


import app.it_academy.fitnessAppUsers.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.FullUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.RegisterByAdminUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.UpdateUserDto;

import java.time.Instant;
import java.util.UUID;

public interface IUserService {

    PageDto<FullUserDto> getAllUsers(Integer pageNumber, Integer pageSize);

    FullUserDto getSingleUser(UUID uuid);

    UUID createUser(RegisterByAdminUserDto userDto);

    UUID updateUser(UUID uuid, Instant updateLastTime, UpdateUserDto userDto);

}
