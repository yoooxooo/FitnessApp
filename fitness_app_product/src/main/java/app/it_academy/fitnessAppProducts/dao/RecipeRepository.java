package app.it_academy.fitnessAppProducts.dao;

import app.it_academy.fitnessAppProducts.domain.Recipe;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends PagingAndSortingRepository<Recipe, UUID> {

        <S extends Recipe> S save(S entity);

        Optional<Recipe> findById(UUID uuid);

        List<Recipe> findAll();
}
