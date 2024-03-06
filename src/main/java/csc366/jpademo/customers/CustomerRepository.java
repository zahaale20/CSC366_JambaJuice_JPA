package csc366.jpademo.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Customer findByName(String name);

    Customer findByPhoneNumber(String phoneNumber);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.name = :newName WHERE c.customerID = :customerId")
    void updateName(@Param("customerId") Long customerId, @Param("newName") String newName);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.phoneNumber = :newPhoneNumber WHERE c.customerID = :customerId")
    void updatePhoneNumber(@Param("customerId") Long customerId, @Param("newPhoneNumber") String newPhoneNumber);

    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.name = :name")
    void deleteByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.phoneNumber = :phoneNumber")
    void deleteByPhoneNumber(@Param("phoneNumber") String phoneNumber);


}