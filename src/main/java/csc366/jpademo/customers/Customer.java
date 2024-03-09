package csc366.jpademo.customers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import java.util.StringJoiner;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;

    @Column(nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;


    public Customer() {}

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", Customer.class.getSimpleName() + "[", "]");
        sj.add(customerID.toString()).add(name).add(phoneNumber);
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return 366;
    }
}