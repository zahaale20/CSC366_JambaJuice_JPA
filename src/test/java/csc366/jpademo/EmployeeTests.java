package csc366.jpademo;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.main.banner-mode=off",
        "spring.jpa.hibernate.ddl-auto=update",
        "logging.level.root=ERROR",
        "logging.level.csc366=DEBUG",

        "logging.level.org.hibernate.SQL=DEBUG",
        "logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE",
        "spring.jpa.properties.hibernate.format_sql=true",
        "spring.jpa.show-sql=false",
        "spring.jpa.properties.hibernate.show_sql=false",

        "logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeTests {

    private final static Logger log = LoggerFactory.getLogger(EmployeeTests.class);

    @Autowired
    private csc366.jpademo.employees.EmployeeRepository employeeRepository;

    private final csc366.jpademo.employees.Employee employee = new csc366.jpademo.employees.Employee("Billy", "Edward", "Goat", "555-555-5555", "billygoat@email.com", "Dish Washer", new Date(), null, null, "Full-Time", new Date(), "123-45-6789", 10000.00, "Male", "White");

    @BeforeEach
    public void setup() {
        employeeRepository.saveAndFlush(employee);
    }

    @Test
    public void testSaveEmployee() {
        csc366.jpademo.employees.Employee found = employeeRepository.findByEmail("billygoat@email.com");
        log.info(found.toString());

        assertNotNull(found);
        assertEquals(employee.getEmail(), found.getEmail());
    }

    @Test
    public void testFindEmployeeByEmail() {
        csc366.jpademo.employees.Employee found = employeeRepository.findByEmail("billygoat@email.com");
        assertNotNull(found);
        assertEquals(employee.getEmail(), found.getEmail());
    }

    @Test
    public void testDeleteEmployee() {
        employeeRepository.delete(employee);
        employeeRepository.flush();
    }

    @Test
    public void testFindAllEmployees() {
        assertNotNull(employeeRepository.findAll());
    }

    @Test
    public void testDeleteByEmployeeId() {
        csc366.jpademo.employees.Employee e = employeeRepository.findByEmail("billygoat@email.com");
        employeeRepository.deleteById(e.getEmployeeID());
        employeeRepository.flush();
    }

    @Test
    public void testFindByNameJpql() {
        List<csc366.jpademo.employees.Employee> employees = employeeRepository.findByNameJpql("Billy");
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
        assertEquals("Billy", employees.get(0).getFirstName());
    }

    @Test
    public void testFindByJobTitleJpql() {
        List<csc366.jpademo.employees.Employee> employees = employeeRepository.findByJobTitleJpql("Dish Washer");
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
        assertTrue(employees.stream().allMatch(e -> e.getJobTitle().equals("Dish Washer")));
    }

    @Test
    public void testFindBySupervisorId() {
        csc366.jpademo.employees.Employee supervisor = new csc366.jpademo.employees.Employee("Joe", "Seph", "Sanders", "555-666-7777", "jos@email.com", "Turtle sitter", new Date(), null, null, "Full-Time", new Date(), "987-65-4321", 80000.00, "Alien", "Alien");
        employee.setSupervisor(supervisor);
        employeeRepository.saveAndFlush(supervisor);
        employeeRepository.saveAndFlush(employee);

        List<csc366.jpademo.employees.Employee> supervisees = employeeRepository.findBySupervisorId(supervisor.getEmployeeID());
        assertNotNull(supervisees);
        assertFalse(supervisees.isEmpty());
        assertTrue(supervisees.contains(employee));
    }

    @Test
    public void testDeleteByEmail() {
        employeeRepository.deleteByEmail("jdoe@email.com");
        employeeRepository.flush();
        csc366.jpademo.employees.Employee found = employeeRepository.findByEmail("jdoe@email.com");
        assertNull(found);
    }

    @Test
    public void testUpdateEmployeeDetails() {
        csc366.jpademo.employees.Employee found = employeeRepository.findByEmail(employee.getEmail());
        String newPhoneNumber = "999-999-9999";
        found.setPhoneNumber(newPhoneNumber);
        employeeRepository.saveAndFlush(found);

        csc366.jpademo.employees.Employee updated = employeeRepository.findByEmail(employee.getEmail());
        assertEquals(newPhoneNumber, updated.getPhoneNumber(), "Phone number should be updated.");
    }

    @Test
    public void testAddAndRemoveSupervisee() {
        csc366.jpademo.employees.Employee supervisee = new csc366.jpademo.employees.Employee("Jane", "D", "Doe", "555-111-2222", "janedoe@email.com", "Developer", new Date(), null, null, "Full-Time", new Date(), "222-33-4444", 20000.00, "Female", "Asian");
        employeeRepository.saveAndFlush(supervisee);

        employee.getSupervisees().add(supervisee);
        supervisee.setSupervisor(employee);
        employeeRepository.saveAndFlush(employee);
        assertTrue(employeeRepository.findBySupervisorId(employee.getEmployeeID()).contains(supervisee), "Supervisee should be added.");

        employee.getSupervisees().remove(supervisee);
        supervisee.setSupervisor(null);
        employeeRepository.saveAndFlush(employee);
        assertFalse(employeeRepository.findBySupervisorId(employee.getEmployeeID()).contains(supervisee), "Supervisee should be removed.");
    }

    @Test
    public void testFindByEmploymentType() {
        List<csc366.jpademo.employees.Employee> fullTimeEmployees = employeeRepository.findByEmploymentType("Full-Time");
        assertNotNull(fullTimeEmployees);
        assertFalse(fullTimeEmployees.isEmpty());
        assertTrue(fullTimeEmployees.stream().allMatch(e -> e.getEmploymentType().equals("Full-Time")), "Should find all full-time employees.");
    }

    @Test
    public void testDuplicateEmail() {
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            csc366.jpademo.employees.Employee duplicateEmailEmployee = new csc366.jpademo.employees.Employee("Tom", "B", "Jerry", "555-222-3333", employee.getEmail(), "Tester", new Date(), null, null, "Part-Time", new Date(), "111-22-3333", 5000.00, "Male", "Hispanic");
            employeeRepository.saveAndFlush(duplicateEmailEmployee);
        });

        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Should throw an exception due to duplicate email.");
    }

}