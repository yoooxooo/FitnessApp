package app.it_academy.fitnessAppUsers.service.api;

import app.it_academy.fitnessAppUsers.domain.User;

import java.util.Optional;

public interface IUserDetailsService {

    Optional<User> findUserByUsername(String name);
}
