package csc366.jpademo.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    Ingredient findByName(String name);

    @Modifying
    @Query("update Ingredient i set i.name = :newName where i.name = :oldName")
    void updateName(@Param("oldName") String oldName, @Param("newName") String newName);
}

