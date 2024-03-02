package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // this class maps to a database table
public class State {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;   // note: no annotation, still included in underlying table
    private Double salesTax;
    private String abbreviation;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "person_id", nullable = true)
    //private Person person;

    public State() { }

    public State(String name, Double salesTax, String abbreviation) {
        this.name = name;
        this.salesTax = salesTax;
        this.abbreviation = abbreviation;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salesTax=" + salesTax +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        State state = (State) object;

        if (!id.equals(state.id)) return false;
        if (name != null ? !name.equals(state.name) : state.name != null) return false;
        if (salesTax != null ? !salesTax.equals(state.salesTax) : state.salesTax != null) return false;
        if (abbreviation != null ? !abbreviation.equals(state.abbreviation) : state.abbreviation != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (salesTax != null ? salesTax.hashCode() : 0);
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        return result;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(Double salesTax) {
        this.salesTax = salesTax;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }


    

}
