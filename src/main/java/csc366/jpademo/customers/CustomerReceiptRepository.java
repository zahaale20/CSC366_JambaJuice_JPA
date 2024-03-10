package csc366.jpademo.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CustomerReceiptRepository extends JpaRepository<CustomerReceipt, Long>{
    List<CustomerReceipt> findByTotal(Double total);

    List<CustomerReceipt> findByDateTime(Date dateTime);

    List<CustomerReceipt> findByTax(Double tax);

    List<CustomerReceipt> findByCustomer(csc366.jpademo.customers.Customer customer);

    List<CustomerReceipt> findByState(csc366.jpademo.customers.State state);

    @Query("SELECT cr from CustomerReceipt cr WHERE cr.customer.name = :name")
    List<CustomerReceipt> findByCustomerName(@Param("name") String name);

    @Query("SELECT cr from CustomerReceipt cr WHERE cr.state.name = :name")
    List<CustomerReceipt> findByStateName(@Param("name") String name);
    
    @Query("SELECT cr from CustomerReceipt cr WHERE cr.state.abbreviation = :abbreviation")
    List<CustomerReceipt> findByStateAbbreviation(@Param("abbreviation") String abbreviation);
    
    @Modifying
    @Transactional
    @Query("UPDATE CustomerReceipt cr SET cr.total = :newTotal WHERE cr.customerReceiptID = :customerReceiptID")
    void updateTotal(@Param("customerReceiptID") Long customerReceiptID, @Param("newTotal") Double newTotal);

    @Modifying
    @Transactional
    @Query("UPDATE CustomerReceipt cr SET cr.dateTime = :newDateTime WHERE cr.customerReceiptID = :customerReceiptID")
    void updateDateTime(@Param("customerReceiptID") Long customerReceiptID, @Param("newDateTime") Date newDateTime);

    @Modifying
    @Transactional
    @Query("UPDATE CustomerReceipt cr SET cr.tax = :newTax WHERE cr.customerReceiptID = :customerReceiptID")
    void updateTax(@Param("customerReceiptID") Long customerReceiptID, @Param("newTax") Double newTax);

    @Modifying
    @Transactional
    @Query("UPDATE CustomerReceipt cr SET cr.customer = :newCustomer WHERE cr.customerReceiptID = :customerReceiptID")
    void updateCustomer(@Param("customerReceiptID") Long customerReceiptID, @Param("newCustomer") csc366.jpademo.customers.Customer newCustomer);

    @Modifying
    @Transactional
    @Query("UPDATE CustomerReceipt cr SET cr.state = :newState WHERE cr.customerReceiptID = :customerReceiptID")
    void updateState(@Param("customerReceiptID") Long customerReceiptID, @Param("newState") csc366.jpademo.customers.State newState);

    @Modifying
    @Transactional
    @Query("DELETE FROM CustomerReceipt cr WHERE cr.dateTime = :dateTime")
    void deleteByDateTime(@Param("dateTime") Date dateTime);

    @Modifying
    @Transactional
    @Query("DELETE FROM CustomerReceipt cr WHERE cr.customer = :customer")
    void deleteByCustomer(@Param("customer") csc366.jpademo.customers.Customer customer);


}