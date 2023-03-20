package app.it_academy.fitnessAppProducts.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "recipes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "dt_create")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant creationDate;

    @Column(name = "dt_update")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @Version
    private Instant updateDate;

    @Column(name = "title")
    private String title;

    @JoinTable(name = "recipe_portion", joinColumns = @JoinColumn (name="id_recipe"), inverseJoinColumns = @JoinColumn (name="id_portion"))
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ProductPortion> composition;

    @Transient
    private int weight;

    @Transient
    private int calories;

    @Transient
    private double proteins;

    @Transient
    private double fats;

    @Transient
    private double carbohydrates;

    public Recipe() {
    }

    public int updateEntityInfo() {
        this.weight = 0;
        this.calories = 0;
        this.proteins = 0;
        this.fats = 0;
        this.carbohydrates = 0;
        for (ProductPortion product : composition) {
            this.weight += product.getWeight();
            this.calories += product.getCalories();
            this.proteins += product.getProteins();
            this.fats += product.getFats();
            this.carbohydrates += product.getCarbohydrates();
        }
        return this.weight;
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

    public List<ProductPortion> getComposition() {
        return composition;
    }


    public void setComposition(List<ProductPortion> composition) {
        this.composition = composition;
        updateEntityInfo();
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
}
