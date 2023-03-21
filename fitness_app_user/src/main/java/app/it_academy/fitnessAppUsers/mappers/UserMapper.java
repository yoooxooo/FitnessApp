package app.it_academy.fitnessAppUsers.mappers;

import app.it_academy.fitnessAppUsers.core.dto.userDto.*;
import app.it_academy.fitnessAppUsers.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public interface UserMapper {

    PasswordEncoder encoder = new BCryptPasswordEncoder();


    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "updateDate", expression = "java(user.getCreationDate())")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "role", expression = "java(app.it_academy.fitnessAppUsers.domain.UserRole.USER)")
    @Mapping(target = "status", expression = "java(app.it_academy.fitnessAppUsers.domain.UserStatus.WAITING_ACTIVATION)")
    @Mapping(target = "password", expression = "java(encoder.encode(dto.getPassword()))")
    User createEntity(RegisterUserDto dto);

    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "updateDate", expression = "java(user.getCreationDate())")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "status", expression = "java(app.it_academy.fitnessAppUsers.domain.UserStatus.ACTIVATED)")
    @Mapping(target = "password", expression = "java(encoder.encode(dto.getPassword()))")
    User createEntity(RegisterByAdminUserDto dto);

    @Mapping(target = "dt_create", source = "creationDate")
    @Mapping(target = "dt_update", source = "updateDate")
    FullUserDto createDto(User user);

    @Mapping(target = "creationDate", source = "oldUser.creationDate")
    @Mapping(target = "updateDate", source = "oldUser.updateDate")
    @Mapping(target = "id", source = "oldUser.id")
    @Mapping(target = "mail", source = "dto.mail")
    @Mapping(target = "fio", source = "dto.fio")
    @Mapping(target = "role", source = "dto.role")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(target = "password", source = "dto.password")
    User updateUser(UpdateUserDto dto, User oldUser);

    @Mapping(target = "userName", source = "user.mail")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "roles", expression = """
            java(new java.util.ArrayList<org.springframework.security.core.authority.SimpleGrantedAuthority>
            (java.util.Collections.singleton
            (new org.springframework.security.core.authority.SimpleGrantedAuthority
            (\"ROLE_\" + user.getRole())
            )))
            """)
    UserDetailsImp getUserDetails(User user);

}
