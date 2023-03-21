package app.it_academy.fitnessAppAudit.utils;

import app.it_academy.fitnessAppAudit.core.dto.usersDto.UserDetailsUuid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public UserDetailsUuid getUser(){
        return (UserDetailsUuid) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
