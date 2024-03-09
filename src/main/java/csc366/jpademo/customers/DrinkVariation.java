package csc366.jpademo.customers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import java.util.StringJoiner;


@Entity
@Table(name = "drink_variation")
public class DrinkVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drinkVariationID;

    @Column(nullable = false)
    private String size;

    @Column(name = "cost_to_make", nullable = false)
    private Double costToMake;

    @Column(name = "price_to_sell", nullable = false)
    private Double priceToSell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drinkID", nullable = false)
    private csc366.jpademo.customers.Drink drink;


    public DrinkVariation() {}

    public DrinkVariation(String size, Double costToMake, Double priceToSell, csc366.jpademo.customers.Drink drink) {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getCostToMake() {
        return costToMake;
    }

    public void setCostToMake(Double costToMake) {
        this.costToMake = costToMake;
    }

    public Double getPriceToSell() {
        return priceToSell;
    }

    public void setPriceToSell(Double priceToSell) {
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
        sj.add(drinkVariationID.toString()).add(size).add(costToMake.toString()).add(priceToSell.toString()).add(drink.toString());
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}