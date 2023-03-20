package app.it_academy.fitnessAppProducts.service.api;

import app.it_academy.fitnessAppProducts.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppProducts.core.dto.recipeDto.CreateRecipeDto;
import app.it_academy.fitnessAppProducts.core.dto.recipeDto.RecipeDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public interface IRecipeService {

    PageDto<RecipeDto> getAllRecipes(Integer page, Integer size);

    void createRecipe(CreateRecipeDto productDto);

    void updateRecipe(UUID uuid, Instant updateLastTime, CreateRecipeDto productDto);
}
