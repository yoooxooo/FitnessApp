package app.it_academy.fitnessAppUsers.dao;

import app.it_academy.fitnessAppUsers.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends PagingAndSortingRepository<User, UUID> {

    <S extends User> S save(S entity);

    Optional<User> findByMail(String mail);

    Optional<User> findById(UUID uuid);
}
