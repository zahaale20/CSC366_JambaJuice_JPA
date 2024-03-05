package csc366.jpademo.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface PaycheckRepository extends JpaRepository<csc366.jpademo.employees.Paycheck, Long> {

    @Query("SELECT p FROM Paycheck p WHERE p.employee.employeeID = :employeeID")
    List<csc366.jpademo.employees.Paycheck> findByEmployeeId(@Param("employeeID") Long employeeID);

    List<csc366.jpademo.employees.Paycheck> findByDateBetween(Date startDate, Date endDate);
}