package csc366.jpademo.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{
    State findByName(String name);
    
    State findByAbbreviation(String abbreviation);

    List<State> findBySalesTaxPercent(Float salesTaxPercent);

    @Query("SELECT s from State s WHERE s.salesTaxPercent >= :minSalesTaxPercent AND s.salesTaxPercent <= :maxSalesTaxPercent")
    List<State> findBySalesTaxPercentRange(@Param("minSalesTaxPercent") Float minSalesTaxPercent, @Param("maxSalesTaxPercent") Float maxSalesTaxPercent);

    @Modifying
    @Transactional
    @Query("UPDATE State s SET s.abbreviation = :newAbbreviation WHERE s.stateID = :stateID")
    void updateAbbreviation(@Param("stateID") Long stateID, @Param("newAbbreviation") String newAbbreviation);

    @Modifying
    @Transactional
    @Query("UPDATE State s SET s.name = :newName WHERE s.stateID = :stateID")
    void updateName(@Param("stateID") Long stateID, @Param("newName") String newName);

    @Modifying
    @Transactional
    @Query("UPDATE State s SET s.salesTaxPercent = :newSalesTaxPercent WHERE s.stateID = :stateID")
    void updateSalesTaxPercent(@Param("stateID") Long stateID, @Param("newSalesTaxPercent") Float newSalesTaxPercent);

    @Modifying
    @Transactional
    @Query("DELETE FROM State s WHERE s.abbreviation = :abbreviation")
    void deleteByAbbreviation(@Param("abbreviation") String abbreviation);

    @Modifying
    @Transactional
    @Query("DELETE FROM State s WHERE s.name = :name")
    void deleteByName(@Param("name") String name);
    

}