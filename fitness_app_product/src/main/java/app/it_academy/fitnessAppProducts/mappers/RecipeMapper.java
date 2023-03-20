package app.it_academy.fitnessAppProducts.mappers;

import app.it_academy.fitnessAppProducts.core.dto.recipeDto.CreateRecipeDto;
import app.it_academy.fitnessAppProducts.core.dto.recipeDto.RecipeDto;
import app.it_academy.fitnessAppProducts.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RecipeMapper {

        @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
        @Mapping(target = "updateDate", expression = "java(java.time.Instant.now())")
        @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
        @Mapping(target = "weight", expression = "java(recipe.updateEntityInfo())")
        Recipe createEntity(CreateRecipeDto dto);


        RecipeDto createDto(Recipe recipe);

        @Mapping(target = "creationDate", source = "oldRecipe.creationDate")
        @Mapping(target = "updateDate", source = "oldRecipe.updateDate")
        @Mapping(target = "title", source = "dto.title")
        @Mapping(target = "composition", source = "dto.composition")
        Recipe updateRecipe(CreateRecipeDto dto, Recipe oldRecipe);

}
