package csc366.jpademo.customers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "customer_receipt")
public class CustomerReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerReceiptID;

    @Column(nullable = false)
    private Float total;

    @Column(name = "date_time", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTime;

    @Column(nullable = false)
    private Float tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID", nullable = false)
    private csc366.jpademo.customers.Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stateID", nullable = false)
    private csc366.jpademo.customers.State state;


    public CustomerReceipt() {}

    public CustomerReceipt(Float total, Date dateTime, Float tax, csc366.jpademo.customers.Customer customer, csc366.jpademo.customers.State state) {
        this.total = total;
        this.dateTime = dateTime;
        this.tax = tax;
        this.customer = customer;
        this.state = state;
    }

    // Getters and setters
    public Long getCustomerReceiptID() {
        return CustomerReceiptID;
    }

    public void setCustomerReceiptID(Long customerReceiptID) {
        this.customerReceiptID = customerReceiptID;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public csc366.jpademo.customers.Customer getCustomer() {
        return customer;
    }

    public void setCustomer(csc366.jpademo.customers.Customer customer) {
        this.customer = customer;
    }

    public csc366.jpademo.customers.State getState() {
        return state;
    }

    public void setState(csc366.jpademo.customers.State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", CustomerReceipt.class.getSimpleName() + "[", "]");
        sj.add(customerReceiptID.toString()).add(total).add(dateTime).add(tax).add(customer).add(state);
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}