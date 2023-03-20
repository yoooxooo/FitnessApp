package app.it_academy.fitnessAppProducts.core.dto.usersDto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserDetailsUuid extends UserDetails {

    UUID getId();

    public void setUserName(String userName);

    public void setPassword(String password);

    public void setId(UUID id);

    public void setRoles(List<SimpleGrantedAuthority> roles);
}
