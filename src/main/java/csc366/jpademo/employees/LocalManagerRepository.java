package csc366.jpademo.employees;

import jdk.vm.ci.meta.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocalManagerRepository extends JpaRepository<LocalManager, Long> {

    // create query to find localmanagers from restaurant id

    @Query("SELECT lm FROM LocalManager lm WHERE :associate MEMBER OF lm.associates")
    List<LocalManager> findByAssociate(@Param("associate") Associate associate);
//
//    @Query("SELECT lm FROM LocalManager lm WHERE lm.regionalManager = :regionalManager")
//    List<LocalManager> findByRegionalManager(@Param("regionalManager") RegionalManager regionalManager);
}
