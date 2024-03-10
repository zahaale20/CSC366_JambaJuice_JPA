package csc366.jpademo.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<csc366.jpademo.employees.Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    csc366.jpademo.employees.Employee findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.employmentType = :employmentType")
    List<Employee> findByEmploymentType(String employmentType);

    @Query("SELECT e FROM Employee e WHERE e.firstName = :name OR e.lastName = :name")
    List<csc366.jpademo.employees.Employee> findByNameJpql(@Param("name") String name);

    @Query("SELECT e FROM Employee e WHERE e.jobTitle = :jobTitle")
    List<csc366.jpademo.employees.Employee> findByJobTitleJpql(@Param("jobTitle") String jobTitle);

    @Query("SELECT e FROM Employee e WHERE e.supervisor.employeeID = :supervisorId")
    List<csc366.jpademo.employees.Employee> findBySupervisorId(@Param("supervisorId") Long supervisorId);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.jobTitle = :newJobTitle WHERE e.employeeID = :employeeId")
    void updateJobTitle(@Param("employeeId") Long employeeId, @Param("newJobTitle") String newJobTitle);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.email = :email")
    void deleteByEmail(@Param("email") String email);

    Employee findBySSN(String s);
}