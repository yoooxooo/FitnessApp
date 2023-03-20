package app.it_academy.fitnessAppUsers.service;

import app.it_academy.fitnessAppUsers.dao.UserDetailsRepository;
import app.it_academy.fitnessAppUsers.domain.User;
import app.it_academy.fitnessAppUsers.service.api.IUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements IUserDetailsService {

    private final UserDetailsRepository dao;

    public UserDetailsService(UserDetailsRepository dao) {
        this.dao = dao;
    }

    @Override
    public Optional<User> findUserByUsername(String name) {
        return dao.findByMail(name);
    }

    public User getSystem() {
        return dao.findByMail("SYSTEM@SYSTEM.SYSTEM").orElseThrow();
    }
}

