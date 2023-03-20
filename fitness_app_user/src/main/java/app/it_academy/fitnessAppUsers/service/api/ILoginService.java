package app.it_academy.fitnessAppUsers.service.api;


import app.it_academy.fitnessAppUsers.core.dto.userDto.FullUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.LoginUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.RegisterUserDto;

import java.util.UUID;

public interface ILoginService {

    UUID register(RegisterUserDto userDto);

    UUID verify(String key, String mail);

    String login(LoginUserDto userDto);

    FullUserDto getCurrentUser();
}
