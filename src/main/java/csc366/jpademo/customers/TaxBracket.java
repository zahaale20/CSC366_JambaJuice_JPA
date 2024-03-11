package csc366.jpademo.customers;

import javax.persistence.*;
import java.util.StringJoiner;


@Entity
@Table(name = "taxBracket")
public class TaxBracket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxBracketID;

    @Column(name="bracket_start",nullable = false)
    private Double bracketStart;

    @Column(name="bracket_end", nullable = false)
    private Double bracketEnd;


    // TODO :
    // add incomeTaxProgessiveID ??

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drinkID", nullable = false)
    private csc366.jpademo.customers.Drink drink;




    public TaxBracket() {}

    public TaxBracket(String abbreviation, String name, Double salesTaxPercent) {
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
        StringJoiner sj = new StringJoiner(",", TaxBracket.class.getSimpleName() + "[", "]");
        sj.add(stateID.toString()).add(abbreviation).add(name).add(salesTaxPercent.toString());
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}