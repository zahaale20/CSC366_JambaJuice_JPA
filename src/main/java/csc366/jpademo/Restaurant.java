package csc366.jpademo;

import csc366.jpademo.customers.CustomerReceipt;
import csc366.jpademo.customers.State;
import csc366.jpademo.employees.LocalManager;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity  // indicates that this class maps to a database table
@Table(
        name = "restaurant"     // may be omitted for default table naming
)
public class Restaurant {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String address;

    @ManyToOne(
            targetEntity = State.class,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany(
            targetEntity = LocalManager.class,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "restaurant"
    )
    private List<LocalManager> localManagers = new ArrayList<>();

    @ManyToOne(
            targetEntity = CustomerReceipt.class,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "customer_receipt_id")
    private CustomerReceipt customerReceipt;

    public Restaurant() {}

    public Restaurant(String address, State state, LocalManager localManager, CustomerReceipt customerReceipt) {
        this.address = address;
        this.state = state;
        this.localManagers.add(localManager);
        this.customerReceipt = customerReceipt;
    }

    public Long getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public CustomerReceipt getCustomerReceipt() {
        return customerReceipt;
    }

    public List<LocalManager> getLocalManagers() {
        return localManagers;
    }

    public void addLocalManager(LocalManager localManager) {
        this.localManagers.add(localManager);
        localManager.setRestaurant(this);
    }

    public void removeLocalManger(LocalManager localManager) {
        this.localManagers.remove(localManager);
        localManager.setRestaurant(null);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Restaurant.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(address).add(String.valueOf(state)).add(String.valueOf(localManagers)).add(String.valueOf(customerReceipt));
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}
