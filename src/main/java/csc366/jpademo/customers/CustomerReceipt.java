package csc366.jpademo.customers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import java.util.StringJoiner;


@Entity
@Table(name = "customer_receipt")
public class CustomerReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerReceiptID;

    @Column(nullable = false)
    private Double total;

    @Column(name = "date_time", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTime;

    @Column(nullable = false)
    private Double tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID", nullable = false)
    private csc366.jpademo.customers.Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stateID", nullable = false)
    private csc366.jpademo.customers.State state;


    public CustomerReceipt() {}

    public CustomerReceipt(Double total, Date dateTime, Double tax, csc366.jpademo.customers.Customer customer, csc366.jpademo.customers.State state) {
        this.total = total;
        this.dateTime = dateTime;
        this.tax = tax;
        this.customer = customer;
        this.state = state;
    }

    // Getters and setters
    public Long getCustomerReceiptID() {
        return customerReceiptID;
    }

    public void setCustomerReceiptID(Long customerReceiptID) {
        this.customerReceiptID = customerReceiptID;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
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
        sj.add(customerReceiptID.toString()).add(total.toString()).add(dateTime.toString()).add(tax.toString()).add(customer.toString()).add(state.toString());
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}