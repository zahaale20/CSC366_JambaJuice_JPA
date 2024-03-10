package csc366.jpademo.supplier;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Entity  // indicates that this class maps to a database table
@Table(
        name = "supply_contract"     // may be omitted for default table naming
)
public class SupplyContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private double cost;

    @NotNull
    @Column
    private String description;

    @ManyToOne(
            targetEntity = Supplier.class,
            cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToOne(
            targetEntity = Transaction.class,
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    // TODO: Link Supplier to SupplyReceiptRow assocation class
//    @ManyToMany(
//            targetEntity = SupplyReceiptRow.class,
//            cascade = CascadeType.REMOVE,
//            fetch = FetchType.LAZY
//    )
//    private List<SupplyReceiptRow> supplyReceiptRows = new ArrayList<>();

    public SupplyContract() {}

    public SupplyContract(double cost, String description) {
        this.cost = cost;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addSupplier(Supplier supplier) {
        supplier.addSupplyContract(this);
        this.supplier = supplier;
    }

    public void removeSupplier(Supplier supplier) {
        supplier.removeSupplyContract(this);
        this.supplier = null;
    }

    public void addTransaction(Transaction transaction) {
        transaction.setSupplyContract(this);
        this.transaction = transaction;
    }

    public void removeTransaction(Transaction transaction) {
        this.transaction = null;
        transaction.setSupplier(null);
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    // TODO: Link Supplier to SupplyReceiptRow assocation class
//    public List<SupplyReceiptRow> getSupplyReceiptRows() {
//        return supplyReceiptRows;
//    }
//
//    public void setSupplyReceiptRows(List<SupplyReceiptRow> supplyReceiptRows) {
//        this.supplyReceiptRows = supplyReceiptRows;
//    }

    @Override
    public String toString() {
        // TODO: Add new relations here
        StringJoiner sj = new StringJoiner(",", SupplyContract.class.getSimpleName() + "[", "]");
        sj.add(id.toString()).add(String.valueOf(cost)).add(description).add(String.valueOf(supplier));
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }

}
