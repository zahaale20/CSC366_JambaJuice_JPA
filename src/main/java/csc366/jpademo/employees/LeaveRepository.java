package csc366.jpademo.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<csc366.jpademo.employees.Leave, Long> {

    List<csc366.jpademo.employees.Leave> findByType(String type);

    List<csc366.jpademo.employees.Leave> findByApprovalStatus(String approvalStatus);
}