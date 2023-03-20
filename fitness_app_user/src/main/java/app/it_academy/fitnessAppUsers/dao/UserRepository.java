package app.it_academy.fitnessAppUsers.dao;

import app.it_academy.fitnessAppUsers.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends PagingAndSortingRepository<User, UUID> {

    <S extends User> S save(S entity);


    Optional<User> findById(UUID primaryKey);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByMail(String mail);
}
