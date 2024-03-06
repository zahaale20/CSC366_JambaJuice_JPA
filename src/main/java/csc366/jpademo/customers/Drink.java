package csc366.jpademo.customers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "drink")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drinkID;

    @Column(nullable = false)
    private String name;


    public Drink() {}

    public Drink(String name) {
        this.name = name;
    }

    // Getters and setters
    public Long getDrinkID() {
        return drinkID;
    }

    public void setDrinkID(Long drinkID) {
        this.drinkID = drinkID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", Drink.class.getSimpleName() + "[", "]");
        sj.add(drinkID.toString()).add(name);
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}