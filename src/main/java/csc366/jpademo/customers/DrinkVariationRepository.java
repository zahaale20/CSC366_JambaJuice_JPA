package csc366.jpademo.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface DrinkVariationRepository extends JpaRepository<DrinkVariation, Long>{
    List<DrinkVariation> findByDrink(csc366.jpademo.customers.Drink drink);

    @Query("SELECT dv from DrinkVariation dv WHERE dv.costToMake >= :minCost AND dv.costToMake <= :maxCost")
    List<DrinkVariation> findByCostToMakeRange(@Param("minCost") Double minCost, @Param("maxCost") Double maxCost);

    @Query("SELECT dv from DrinkVariation dv WHERE dv.priceToSell >= :minPrice AND dv.priceToSell <= :maxPrice")
    List<DrinkVariation> findByPriceToSellRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    @Query("SELECT dv from DrinkVariation dv WHERE dv.priceToSell - dv.costToMake >= :minProfit")
    List<DrinkVariation> findByMinProfit(@Param("minProfit") Double minProfit);
    
    @Query("SELECT dv from DrinkVariation dv WHERE dv.priceToSell - dv.costToMake <= :maxProfit")
    List<DrinkVariation> findByMaxProfit(@Param("maxProfit") Double maxProfit);
    
    @Modifying
    @Transactional
    @Query("UPDATE DrinkVariation dv SET dv.size = :newSize WHERE dv.drinkVariationID = :drinkVariationID")
    void updateSize(@Param("drinkVariationID") Long drinkVariationID, @Param("newSize") String newSize);

    @Modifying
    @Transactional
    @Query("UPDATE DrinkVariation dv SET dv.costToMake = :newCostToMake WHERE dv.drinkVariationID = :drinkVariationID")
    void updateCostToMake(@Param("drinkVariationID") Long drinkVariationID, @Param("newCostToMake") Double newCostToMake);

    @Modifying
    @Transactional
    @Query("UPDATE DrinkVariation dv SET dv.priceToSell = :newPriceToSell WHERE dv.drinkVariationID = :drinkVariationID")
    void updatePriceToSell(@Param("drinkVariationID") Long drinkVariationID, @Param("newPriceToSell") Double newPriceToSell);

    @Modifying
    @Transactional
    @Query("UPDATE DrinkVariation dv SET dv.drink = :newDrink WHERE dv.drinkVariationID = :drinkVariationID")
    void updateDrink(@Param("drinkVariationID") Long drinkVariationID, @Param("newDrink") csc366.jpademo.customers.Drink drink);

    @Modifying
    @Transactional
    @Query("DELETE FROM DrinkVariation dv WHERE dv.drink = :drink")
    void deleteByDrink(@Param("drink") csc366.jpademo.customers.Drink drink);

}