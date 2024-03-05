package csc366.jpademo.supplier;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity  // indicates that this class maps to a database table
@Table(
        name = "ingredient"     // may be omitted for default table naming
)
public class Ingredient {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "num_unit")
    private int numUnit;

    private String description;

    @OneToMany(
            targetEntity = Transaction.class,
            mappedBy = "transaction",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Transaction> transactions = new ArrayList<>();

    public Ingredient() {}

    public Ingredient(String name, int numUnit, String description) {
        this.name = name;
        this.numUnit = numUnit;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getNumUnit() {
        return this.numUnit;
    }

    public void setNumUnit(int numUnit) {
        this.numUnit = numUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.addIngredient(this);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.removeIngredient(this);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Ingredient.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(name).add(String.valueOf(numUnit)).add(description).add("transactions="+transactions.toString());
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}
