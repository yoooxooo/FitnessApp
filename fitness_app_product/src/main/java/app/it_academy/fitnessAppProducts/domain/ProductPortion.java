package app.it_academy.fitnessAppProducts.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name = "portions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductPortion {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "portion_product", joinColumns = @JoinColumn (name="id_portion"), inverseJoinColumns = @JoinColumn (name="id_product"))
    private Product product;
    @Column(name = "weight")
    private int weight;
    @Transient
    private int calories;
    @Transient
    private double proteins;
    @Transient
    private double fats;
    @Transient
    private double carbohydrates;

    public ProductPortion() {
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public int getWeight() {
        return weight;
    }

    public int getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    @Autowired
    public void setProduct(Product product) {
        this.product = product;
    }
    @Autowired
    public void setWeight(int weight) {
        this.weight = weight;
        updateCPFC();
    }

    public void updateCPFC() {
        setCalories();
        setProteins();
        setFats();
        setCarbohydrates();
    }

    public void setCalories() {
        this.calories = (int) (product.getCalories()*((double) weight/product.getWeight()));
    }

    public void setProteins() {
        this.proteins = product.getProteins()*((double) weight /product.getWeight());
    }

    public void setFats() {
        this.fats = product.getFats()*((double) weight /product.getWeight());
    }

    public void setCarbohydrates() {
        this.carbohydrates = product.getProteins()*((double) weight /product.getWeight());
    }
}
