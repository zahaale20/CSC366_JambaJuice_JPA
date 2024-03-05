package csc366.jpademo.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// This class will be automatically implemented by Spring and made available as a "Bean" named personRepository
@Repository
public interface SupplyContractRepository extends JpaRepository<SupplyContract, Long>{
    SupplyContract findById(String id);

    @Modifying
    @Query("update SupplyContract sc set sc.cost = :newCost where sc.cost = :oldCost")
    void updateCost(@Param("oldCost") double oldCost, @Param("newCost") double newCost);
}
