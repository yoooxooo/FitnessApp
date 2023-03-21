package app.it_academy.fitnessAppProducts.web.controllers;

import app.it_academy.fitnessAppProducts.core.audit.annotations.SecureCheck;
import app.it_academy.fitnessAppProducts.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppProducts.core.dto.recipeDto.CreateRecipeDto;
import app.it_academy.fitnessAppProducts.core.dto.recipeDto.RecipeDto;
import app.it_academy.fitnessAppProducts.service.api.IRecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final IRecipeService service;

    public RecipeController(IRecipeService service) {
        this.service = service;
    }

    @GetMapping
    @SecureCheck(role = "USER")
    public ResponseEntity<PageDto<RecipeDto>> getAllRecipes(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok().body(service.getAllRecipes(page, size));
    }

    @PostMapping
    @SecureCheck(role = "ADMIN")
    public ResponseEntity<String> createRecipe(@RequestBody CreateRecipeDto recipeDto) {
        service.createRecipe(recipeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/{uuid}/dt_update/{dt_update}")
    @SecureCheck(role = "ADMIN")
    public ResponseEntity<String> updateRecipe(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("dt_update") Instant localDateTime,
            @RequestBody CreateRecipeDto recipeDto
    ) {
        service.updateRecipe(uuid, localDateTime, recipeDto);
        return ResponseEntity.ok().build();
    }
}
