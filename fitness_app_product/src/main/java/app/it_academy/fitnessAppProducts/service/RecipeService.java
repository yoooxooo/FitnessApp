package app.it_academy.fitnessAppProducts.service;

import app.it_academy.fitnessAppProducts.core.audit.annotations.Audited;
import app.it_academy.fitnessAppProducts.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppProducts.core.dto.recipeDto.CreateRecipeDto;
import app.it_academy.fitnessAppProducts.core.dto.recipeDto.RecipeDto;
import app.it_academy.fitnessAppProducts.core.exceptions.ErrorObject;
import app.it_academy.fitnessAppProducts.core.exceptions.custom.FieldValidationException;
import app.it_academy.fitnessAppProducts.dao.RecipeRepository;
import app.it_academy.fitnessAppProducts.domain.Product;
import app.it_academy.fitnessAppProducts.domain.ProductPortion;
import app.it_academy.fitnessAppProducts.domain.Recipe;
import app.it_academy.fitnessAppProducts.mappers.PageMapper;
import app.it_academy.fitnessAppProducts.mappers.RecipeMapper;
import app.it_academy.fitnessAppProducts.service.api.IProductService;
import app.it_academy.fitnessAppProducts.service.api.IRecipeService;
import jakarta.persistence.OptimisticLockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecipeService implements IRecipeService {

    private final RecipeRepository repository;

    private final IProductService productService;

    private final PageMapper<RecipeDto> pageMapper;

    private final RecipeMapper recipeMapper;

    public RecipeService(RecipeRepository recipeRepository, IProductService productService, RecipeMapper recipeMapper, PageMapper<RecipeDto> pageMapper) {
        this.repository = recipeRepository;
        this.productService = productService;
        this.recipeMapper = recipeMapper;
        this.pageMapper = pageMapper;
    }

    @Override
    public PageDto<RecipeDto> getAllRecipes(Integer pageNumber, Integer pageSize) {
        if (pageNumber < 0 || pageSize < 1) {
            throw new IllegalArgumentException("Страницы с такими параметрами не существует");
        }
        Page<Recipe> page;
        if ((page = repository.findAll(PageRequest.of(pageNumber, pageSize))).getTotalPages() < pageNumber + 1) {
            throw new IllegalArgumentException("Общее количество страниц меньше чем номер запрашиваемой");
        }
        for (Recipe recipe: page.getContent()) {
            for (ProductPortion pp: recipe.getComposition()) {
               pp.updateCPFC();
            }
            recipe.updateEntityInfo();
        }
        return pageMapper.toDto(page.map(recipeMapper::createDto));
    }

    @Override
    @Audited(operationType = "CREATION", essenceType = "RECIPE")
    public UUID createRecipe(CreateRecipeDto recipeDto) {
        List<ErrorObject> errors;
        if ((errors = recipeDto.checkFields()).isEmpty()) {
            List<ProductPortion> portions = new ArrayList<>();
            checkProducts(recipeDto, portions);
            recipeDto.setComposition(portions);
            return repository.save(recipeMapper.createEntity(recipeDto)).getId();
        } else {
            throw new FieldValidationException("Validation Error", errors);
        }
    }

    @Override
    @Audited(operationType = "UPDATE", essenceType = "RECIPE")
    public UUID updateRecipe(UUID uuid, Instant updateLastTime, CreateRecipeDto recipeDto) {
        Recipe bufferedRecipe = repository.findById(uuid).orElseThrow();
        List<ProductPortion> portions = new ArrayList<>();
        Long checkUpdateTimeLong = bufferedRecipe.getUpdateDate().getEpochSecond();
        Long lastUpdateTimeLong = updateLastTime.getEpochSecond();
        if (lastUpdateTimeLong.equals(checkUpdateTimeLong)) {
            checkProducts(recipeDto, portions);
            recipeDto.setComposition(portions);
            return repository.save(recipeMapper.updateRecipe(recipeDto.combine(bufferedRecipe), bufferedRecipe)).getId();
        } else throw new OptimisticLockException("Сущность уже была изменена");
    }

    private void checkProducts(CreateRecipeDto recipeDto, List<ProductPortion> portions) {
        for (ProductPortion portion : recipeDto.getComposition()) {
            Product product = productService.findById(portion.getProduct().getId());
            portion.setProduct(product);
            portion.updateCPFC();
            portions.add(portion);
        }
    }
}
