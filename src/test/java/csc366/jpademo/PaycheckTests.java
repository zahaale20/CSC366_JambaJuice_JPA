package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import csc366.jpademo.employees.Paycheck;
import csc366.jpademo.employees.PaycheckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class PaycheckTests {

    private final static Logger log = LoggerFactory.getLogger(PaycheckTests.class);

    @Autowired
    private PaycheckRepository paycheckRepository;

    @Autowired
    private csc366.jpademo.employees.EmployeeRepository employeeRepository;

    private csc366.jpademo.employees.Employee employee;
    private Paycheck paycheck;

    @BeforeEach
    public void setup() {
        employee = new csc366.jpademo.employees.Employee("John", "Edward", "Doe", "555-555-5555", "jdoe@example.com", "Manager", new Date(), null, null, "Full-Time", new Date(), "123-45-6789", 60000.00, "Male", "Caucasian");
        employeeRepository.saveAndFlush(employee);

        Date paycheckDate = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24)); // 1 day ago
        paycheck = new Paycheck(employee, paycheckDate, 2000.00, 40.0, 300.00);
        paycheckRepository.saveAndFlush(paycheck);
    }

    @Test
    @Order(1)
    public void testSavePaycheck() {
        assertNotNull(paycheck.getPaycheckID());
        log.info(paycheck.toString());

        Paycheck found = paycheckRepository.findById(paycheck.getPaycheckID()).orElse(null);
        assertNotNull(found);
        assertEquals(paycheck.getPaycheckID(), found.getPaycheckID());
    }

    @Test
    @Order(2)
    public void testFindByEmployeeId() {
        List<Paycheck> found = paycheckRepository.findByEmployeeId(employee.getEmployeeID());
        assertNotNull(found);
        assertEquals(1, found.size());
    }

    @Test
    @Order(3)
    public void testFindByDateBetween() {
        Date startDate = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 2)); // 2 days ago
        Date endDate = new Date(); // Current time

        List<Paycheck> found = paycheckRepository.findByDateBetween(startDate, endDate);
        assertNotNull(found);
        assertEquals(1, found.size());
    }
}