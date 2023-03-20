package app.it_academy.fitnessAppUsers.dao;

import app.it_academy.fitnessAppUsers.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDetailsRepository extends PagingAndSortingRepository<User, UUID> {

    Optional<User> findByMail(String mail);
}
