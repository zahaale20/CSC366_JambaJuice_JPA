package csc366.jpademo.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyContractRepository extends JpaRepository<SupplyContract, Long>{
    SupplyContract findById(String id);

    @Query("from SupplyContract s where s.supplier.firstName = :name or s.supplier.lastName = :name")
    SupplyContract findBySupplierName(@Param("name") String name);

    @Modifying
    @Query("update SupplyContract sc set sc.cost = :newCost where sc.cost = :oldCost")
    void updateCost(@Param("oldCost") double oldCost, @Param("newCost") double newCost);
}
