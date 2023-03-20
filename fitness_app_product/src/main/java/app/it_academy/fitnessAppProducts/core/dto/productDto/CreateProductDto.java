package app.it_academy.fitnessAppProducts.core.dto.productDto;

import app.it_academy.fitnessAppProducts.core.exceptions.ErrorObject;
import app.it_academy.fitnessAppProducts.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class CreateProductDto {

    private String title;

    private int weight;

    private int calories;

    private double proteins;

    private double fats;

    private double carbohydrates;

    public CreateProductDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public List<ErrorObject> checkFields() {
        List<ErrorObject> errorFields = new ArrayList<>();
        if (getCalories() < 0) {
            errorFields.add(new ErrorObject("Поле не может быть отрицательным", "Calories"));
        }
        if (getWeight() < 0) {
            errorFields.add(new ErrorObject("Поле не может быть отрицательным", "Weight"));
        }
        if (getProteins() < 0) {
            errorFields.add(new ErrorObject("Поле не может быть отрицательным", "Proteins"));
        }
        if (getFats() < 0) {
            errorFields.add(new ErrorObject("Поле не может быть отрицательным", "Fats"));
        }
        if (getCarbohydrates() < 0) {
            errorFields.add(new ErrorObject("Поле не может быть отрицательным", "Carbohydrates"));
        }
        if (getTitle() == null && getTitle().isEmpty()) {
            errorFields.add(new ErrorObject("Поле должно быть заполнено", "Title"));
        }
        return errorFields;
    }

    public CreateProductDto combine(Product product) {
        if (title == null) {
            title = product.getTitle();
        }
        if (weight <= 0) {
            weight = product.getWeight();
        }
        if (calories <= 0) {
            calories = product.getCalories();
        }
        if (proteins <= 0) {
            proteins = product.getProteins();
        }
        if (fats <= 0) {
            fats = product.getFats();
        }
        if (carbohydrates <= 0) {
            carbohydrates = product.getCarbohydrates();
        }
        return this;
    }
}
