package csc366.jpademo.customers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "drink_variation")
public class DrinkVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drinkVariationID;

    @Column(nullable = false)
    private String size;

    @Column(name = "cost_to_make", nullable = false)
    private Float costToMake;

    @Column(name = "price_to_sell", nullable = false)
    private Float priceToSell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drinkID", nullable = false)
    private csc366.jpademo.customers.Drink drink;


    public DrinkVariation() {}

    public DrinkVariation(String size, Float costToMake, Float priceToSell, csc366.jpademo.customers.Drink drink) {
        this.size = size;
        this.costToMake = costToMake;
        this.priceToSell = priceToSell;
        this.drink = drink;
    }

    // Getters and setters
    public Long getDrinkVariationID() {
        return drinkVariationID;
    }

    public void setDrinkVariationID(Long drinkVariationID) {
        this.drinkVariationID = drinkVariationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCostToMake() {
        return costToMake;
    }

    public void setCostToMake(Float costToMake) {
        this.costToMake = costToMake;
    }

    public Float getPriceToSell() {
        return priceToSell;
    }

    public void setPriceToSell(Float priceToSell) {
        this.priceToSell = priceToSell;
    }

    public csc366.jpademo.customers.Drink getDrink() {
        return drink;
    }

    public void setDrink(csc366.jpademo.customers.Drink drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", Customer.class.getSimpleName() + "[", "]");
        sj.add(drinkVariationID.toString()).add(name).add(costToMake).add(priceToSell).add(drink);
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}