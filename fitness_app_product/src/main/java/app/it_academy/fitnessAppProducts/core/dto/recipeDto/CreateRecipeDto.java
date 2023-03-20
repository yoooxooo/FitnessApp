package app.it_academy.fitnessAppProducts.core.dto.recipeDto;

import app.it_academy.fitnessAppProducts.core.dto.productDto.CreateProductDto;
import app.it_academy.fitnessAppProducts.core.exceptions.ErrorObject;
import app.it_academy.fitnessAppProducts.domain.Product;
import app.it_academy.fitnessAppProducts.domain.ProductPortion;
import app.it_academy.fitnessAppProducts.domain.Recipe;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeDto {

    private List<ProductPortion> composition;

    private String title;

    public CreateRecipeDto() {
    }

    public List<ProductPortion> getComposition() {
        return composition;
    }

    public void setComposition(List<ProductPortion> composition) {
        this.composition = composition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ErrorObject> checkFields() {
        List<ErrorObject> errorFields = new ArrayList<>();
        int n = 0;
        for (ProductPortion p : composition) {
            n++;
            if(p.getWeight() <= 0) {
                errorFields.add(new ErrorObject("Поле должно быть положительным", "Weight #" + n));
            }
            if(p.getProduct().getId() == null) {
                errorFields.add(new ErrorObject("Поле должно быть заполненным", "Product Id #" + n));
            }
        }
        if (getTitle() == null && getTitle().isEmpty()) {
            errorFields.add(new ErrorObject("Поле должно быть заполнено", "Title"));
        }
        return errorFields;
    }

    public CreateRecipeDto combine(Recipe recipe) {
        if (title == null) {
            title = recipe.getTitle();
        }
        if (composition == null || composition.isEmpty()) {
            composition = recipe.getComposition();
        }
        return this;
    }
}
