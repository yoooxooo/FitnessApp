package app.it_academy.fitnessAppProducts.dao;


import app.it_academy.fitnessAppProducts.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends PagingAndSortingRepository<Product, UUID> {

    <S extends Product> S save(S entity);

    Optional<Product> findById(UUID uuid);

    Page<Product> findAll(Pageable pageable);
}
