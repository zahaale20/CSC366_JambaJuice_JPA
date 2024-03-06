package csc366.jpademo.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long>{
    Drink findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Drink d SET d.name = :newName WHERE d.drinkID = :drinkID")
    void updateName(@Param("drinkID") Long drinkID, @Param("newName") String newName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Drink d WHERE d.name = :name")
    void deleteByName(@Param("name") String name);

}