package app.it_academy.fitnessAppProducts.core.dto.productDto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class ProductDto {

    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant creationDate;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant updateDate;

    private String title;

    private int weight;

    private BigDecimal calories;

    private BigDecimal proteins;

    private BigDecimal fats;

    private BigDecimal carbohydrates;

    public ProductDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
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

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = BigDecimal.valueOf(calories);
    }

    public BigDecimal getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = BigDecimal.valueOf(proteins);
    }

    public BigDecimal getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = BigDecimal.valueOf(fats);
    }

    public BigDecimal getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = BigDecimal.valueOf(carbohydrates);
    }
}
