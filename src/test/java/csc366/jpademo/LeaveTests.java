package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import csc366.jpademo.employees.Leave;
import csc366.jpademo.employees.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class LeaveTests {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private csc366.jpademo.EmployeeRepository employeeRepository;

    private csc366.jpademo.Employee employee;
    private Leave leave;

    @BeforeEach
    public void setup() {
        employee = new csc366.jpademo.Employee("Jane", "Q.", "Public", "555-123-4567", "jpublic@example.com", "Developer", new Date(), null, null, "Full-Time", new Date(), "987-65-4321", 70000.00, "Female", "Not Specified");
        employeeRepository.saveAndFlush(employee);

        leave = new Leave(employee, "Annual", new Date(), new Date(), "Pending", "Family vacation");
        leaveRepository.saveAndFlush(leave);
    }

    @Test
    @Order(1)
    public void testSaveLeave() {
        assertNotNull(leave.getLeaveID());
        Leave found = leaveRepository.findById(leave.getLeaveID()).orElse(null);
        assertNotNull(found);
        assertEquals(leave.getLeaveID(), found.getLeaveID());
    }

    @Test
    @Order(2)
    public void testFindByType() {
        List<Leave> found = leaveRepository.findByType("Annual");
        assertNotNull(found);
        assertEquals(1, found.size());
    }

    @Test
    @Order(3)
    public void testFindByApprovalStatus() {
        List<Leave> found = leaveRepository.findByApprovalStatus("Pending");
        assertNotNull(found);
        assertEquals(1, found.size());
    }
}