package csc366.jpademo.employees;

import jdk.vm.ci.meta.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface AssociateRepository extends JpaRepository <Associate, Long> {

    @Query("SELECT a FROM Associate a WHERE :localManager MEMBER OF a.localManagers")
    List<Associate> findByLocalManager(@Param("localManager") LocalManager localManager);
}