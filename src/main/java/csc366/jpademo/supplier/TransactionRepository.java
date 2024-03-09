package csc366.jpademo.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// This class will be automatically implemented by Spring and made available as a "Bean" named personRepository
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    Transaction findById(String id);

    @Query("from Transaction s where s.supplier.firstName = :name or s.supplier.lastName = :name")
    Transaction findBySupplierName(@Param("name") String name);

//    @Modifying
//    @Query("update Transaction t set t.numCase = :newCase where t.numCase = :oldCase")
//    void updateCost(@Param("oldCost") double oldCase, @Param("newCost") double newCase);
}
