package app.it_academy.fitnessAppProducts.service;

import app.it_academy.fitnessAppProducts.core.dto.usersDto.UserDetailsUuid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public UserDetailsUuid getUser(){
        return (UserDetailsUuid) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
