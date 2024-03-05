package csc366.jpademo.supplier;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity  // indicates that this class maps to a database table
@Table(
        name = "transaction"     // may be omitted for default table naming
)
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="num_case")
    private int numCase;

    @Column
    private String notes;

    @OneToOne(
            targetEntity = SupplyContract.class,
            mappedBy = "supply_contract",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private SupplyContract supplyContract;

    @ManyToOne(
            targetEntity = Supplier.class,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private Supplier supplier;

    @OneToMany(
            targetEntity = Ingredient.class,
            mappedBy = "ingredient",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    // TODO: Link Transation to DrinkVariation table
//    @ManyToOne(
//            targetEntity = DrinkVariation.class,
//            cascade = CascadeType.REMOVE,
//            fetch = FetchType.LAZY,
//            optional = false
//    )
//    private DrinkVariation drinkVariation;

    public Transaction() { }

    public Transaction(int numCase, String notes) {
        this.numCase = numCase;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getNumCase() {
        return this.numCase;
    }
    public void setNumCase(int numCase) {
        this.numCase = numCase;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplyContract(SupplyContract supplyContract) {
        this.supplyContract = supplyContract;
    }

    public SupplyContract getSupplyContract() {
        return supplyContract;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        ingredient.addTransaction(this);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
        ingredient.removeTransaction(this);
    }

    public void addSupplier(Supplier supplier) {
        supplier.addTransaction(this);
        this.supplier = supplier;
    }

    public void removeSupplier(Supplier supplier) {
        supplier.removeTransaction(this);
        this.supplier = null;
    }

    public void addSupplyContract(SupplyContract supplyContract) {
        this.supplyContract = supplyContract;
        supplyContract.addTransaction(this);
    }

    public void removeSupplyContract(SupplyContract supplyContract) {
        this.supplyContract = null;
        supplyContract.removeTransaction(this);
    }

    // TODO: Link Transaction to DrinkVariation table
//    public DrinkVariation getDrinkVariation() {
//        return drinkVariation;
//    }
//
//    public void setDrinkVariation(DrinkVariation dv) {
//        this.drinkVariation = dv;
//    }

    @Override
    public String toString() {
        // TODO: Add new relations here
        StringJoiner sj = new StringJoiner("," , SupplyContract.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(String.valueOf(numCase)).add(notes).add(supplyContract.toString()).add(supplier.toString()).add("ingredients="+ingredients.toString());
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}
