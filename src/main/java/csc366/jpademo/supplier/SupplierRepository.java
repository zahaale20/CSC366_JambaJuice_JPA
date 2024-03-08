package csc366.jpademo.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
    Supplier findByFirstName(String firstName);

    @Query("from Supplier s where s.firstName = :name or s.lastName = :name")
    Supplier findByNameJpql(@Param("name") String name);

//    @Query("select s from Supplier s join s.phone phone where s.firstName = :name or s.lastName = :name")
//    Supplier findByNameWithPhoneJpql(@Param("name") String name);

    @Modifying
    @Query("update Supplier s set s.firstName = :newName where s.firstName = :oldName")
    void updateFirstName(@Param("oldName") String oldName, @Param("newName") String newName);

    @Modifying
    @Query("update Supplier s set s.lastName = :newName where s.lastName = :oldName")
    void updateLastName(@Param("oldName") String oldName, @Param("newName") String newName);
}

