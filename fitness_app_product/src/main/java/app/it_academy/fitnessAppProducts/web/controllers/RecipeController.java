package app.it_academy.fitnessAppProducts.web.controllers;

import app.it_academy.fitnessAppProducts.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppProducts.core.dto.recipeDto.CreateRecipeDto;
import app.it_academy.fitnessAppProducts.core.dto.recipeDto.RecipeDto;
import app.it_academy.fitnessAppProducts.service.api.IRecipeService;
import app.it_academy.fitnessAppProducts.utils.InstantConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jdk.jfr.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final IRecipeService service;

    public RecipeController(IRecipeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PageDto<RecipeDto>> getAllRecipes(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok().body(service.getAllRecipes(page, size));
    }

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody CreateRecipeDto recipeDto) {
        service.createRecipe(recipeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> updateRecipe(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("dt_update") Instant localDateTime,
            @RequestBody CreateRecipeDto recipeDto
    ) {
        service.updateRecipe(uuid, localDateTime, recipeDto);
        return ResponseEntity.ok().build();
    }
}
