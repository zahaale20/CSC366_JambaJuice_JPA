package csc366.jpademo.customers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import java.util.StringJoiner;


@Entity
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateID;

    @Column(nullable = false)
    private String abbreviation;

    @Column(nullable = false)
    private String name;

    @Column(name="sales_tax_percent", nullable = false)
    private Double salesTaxPercent;


    // TODO : 
    // add incomeTaxProgessiveID ??



    public State() {}

    public State(String abbreviation, String name, Double salesTaxPercent) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.salesTaxPercent = salesTaxPercent;
    }

    // Getters and setters
    public Long getStateID() {
        return stateID;
    }

    public void setStateID(Long stateID) {
        this.stateID = stateID;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalesTaxPercent() {
        return salesTaxPercent;
    }

    public void setSalesTaxPercent(Double salesTaxPercent) {
        this.salesTaxPercent = salesTaxPercent;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", State.class.getSimpleName() + "[", "]");
        sj.add(stateID.toString()).add(abbreviation).add(name).add(salesTaxPercent.toString());
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}