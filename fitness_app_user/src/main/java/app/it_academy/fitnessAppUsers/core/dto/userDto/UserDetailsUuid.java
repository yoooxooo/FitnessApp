package app.it_academy.fitnessAppUsers.core.dto.userDto;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserDetailsUuid extends UserDetails {

    UUID getId();
}
