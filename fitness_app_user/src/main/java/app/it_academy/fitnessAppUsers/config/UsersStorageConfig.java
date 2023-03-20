package app.it_academy.fitnessAppUsers.config;

import app.it_academy.fitnessAppUsers.core.dto.userDto.RegisterByAdminUserDto;
import app.it_academy.fitnessAppUsers.domain.UserRole;
import app.it_academy.fitnessAppUsers.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class UsersStorageConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public boolean AddingAdminInApp(PasswordEncoder encoder, UserService manager) {
        try {
            RegisterByAdminUserDto system = new RegisterByAdminUserDto();
            system.setRole(UserRole.ADMIN);
            system.setFio("SYSTEM");
            system.setMail("SYSTEM@SYSTEM.SYSTEM");
            system.setPassword("SYSTEM");
            manager.createUser(system);

            RegisterByAdminUserDto admin = new RegisterByAdminUserDto();
            admin.setRole(UserRole.ADMIN);
            admin.setFio("ADMIN");
            admin.setMail("ADMINMAIL@mail.ru");
            admin.setPassword("1122334455");
            manager.createUser(admin);

        } catch (RuntimeException e) {
            //всё ок, уже есть
        }

        try {
        RegisterByAdminUserDto system = new RegisterByAdminUserDto();
        system.setRole(UserRole.ADMIN);
        system.setFio("SYSTEM");
        system.setMail("SYSTEM@SYSTEM.SYSTEM");
        system.setPassword("SYSTEM");
        manager.createUser(system);
        } catch (RuntimeException e) {
            //всё ок, уже есть
        }
        return true;
    }
}
