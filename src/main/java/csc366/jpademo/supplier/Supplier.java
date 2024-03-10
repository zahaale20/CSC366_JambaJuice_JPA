package csc366.jpademo.supplier;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity  // indicates that this class maps to a database table
@Table(
        name = "supplier",     // may be omitted for default table naming
        uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "last_name"}) // requires @Column(name=...)
)
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Column
    private String phone;

    @NotNull
    @Column
    private String email;

    // TODO: Link Supplier to State table
//    @ManyToOne(
//            targetEntity = "state",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            optional = false
//    )
//    private State state;

    @OneToMany(
            targetEntity = SupplyContract.class,
            mappedBy = "supplier",       // join column should be in *Address*
            cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address
            orphanRemoval = true,      // address records that are no longer attached to a person are removed
            fetch = FetchType.LAZY
    )
    private List<SupplyContract> supplyContracts = new ArrayList<>();

    @OneToMany(
            targetEntity = Transaction.class,
            mappedBy = "supplier",       // join column should be in *Address*
            cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address
            orphanRemoval = true,      // address records that are no longer attached to a person are removed
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions = new ArrayList<>();

    public Supplier() {}

    public Supplier(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone(String phone) {
        return this.phone;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setSupplier(this);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.setSupplier(null);
    }

    public void addSupplyContract(SupplyContract supplyContract) {
        supplyContracts.add(supplyContract);
        supplyContract.setSupplier(this);
    }

    public void removeSupplyContract(SupplyContract supplyContract) {
        supplyContracts.remove(supplyContract);
        supplyContract.setSupplier(null);
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public List<SupplyContract> getSupplyContracts() {
        return this.supplyContracts;
    }

    // TODO: Link Supplier to State table
//    public State getState() {
//        return this.state;
//    }
//
//    public void setState(State state) {
//        this.state = state;
//    }

    @Override
    public String toString() {
        // TODO: Add new relations here
        StringJoiner sj = new StringJoiner("," , Supplier.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(firstName).add(lastName).add(phone).add(email);
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}
