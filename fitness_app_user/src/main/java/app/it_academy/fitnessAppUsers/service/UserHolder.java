package app.it_academy.fitnessAppUsers.service;

import app.it_academy.fitnessAppUsers.core.dto.userDto.UserDetailsUuid;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public UserDetailsUuid getUser(){
        return (UserDetailsUuid) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Object getSecurity(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
