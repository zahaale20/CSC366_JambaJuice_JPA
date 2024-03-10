package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import csc366.jpademo.employees.*;

import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

// Demo0: Add, list, and remove Person instances

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.main.banner-mode=off",
        "spring.jpa.hibernate.ddl-auto=update",
        "logging.level.root=ERROR",
        "logging.level.csc366=DEBUG",

        "logging.level.org.hibernate.SQL=DEBUG",
        "logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
        "spring.jpa.properties.hibernate.format_sql=true",
        "spring.jpa.show-sql=false",   // prevent duplicate logging
        "spring.jpa.properties.hibernate.show_sql=false",

        "logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
@TestMethodOrder(OrderAnnotation.class)
public class AssociateTests {

    private final static Logger log = LoggerFactory.getLogger(AssociateTests.class);

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private LocalManagerRepository localManagerRepository;

    private final Associate associate = new Associate();  // "reference" Associate
    private final LocalManager localManager = new LocalManager();

    private void localManagerSetUp() {
        localManager.setFirstName("Vince");
        localManager.setLastName("Vaughn");
        localManager.setPhoneNumber("222-222-2222");
        localManager.setEmail("vvaughn@yahoo.com");
        localManager.setJobTitle("Store Manager");
        localManager.setDateStart(new Date());
        localManager.setEmploymentType("Part-time");
        localManager.setSSN("987-654-3210");
        localManager.setDateOfBirth(new Date());
        localManager.setSalary(120000.00);
        localManager.setGender("Male");
        localManager.setEthnicity("White");

    }
    private void associateSetUp() {
        associate.setFirstName("Owen");
        associate.setLastName("Wilson");
        associate.setPhoneNumber("888-888-8888");
        associate.setEmail("owilson@gmail.com");
        associate.setJobTitle("Fry cook");
        associate.setDateStart(new Date());
        associate.setEmploymentType("Full-time");
        associate.setSSN("123-45-6789");
        associate.setDateOfBirth(new Date());
        associate.setSalary(100000.00);
        associate.setGender("Male");
        associate.setEthnicity("White");
        associate.addLocalManager(localManager);
    }

    @BeforeEach
    private void setup() {
        localManagerSetUp();
        associateSetUp();
        localManagerRepository.saveAndFlush(localManager);
        associateRepository.saveAndFlush(associate);
    }

    @Test
    @Order(1)
    public void testGetAssociateByLocalManager() {
        Associate associate2 = associateRepository.findByLocalManager(localManager).get(0);

        assertNotNull(associate);
        assertEquals(associate2.getFirstName(), associate.getFirstName());
        assertEquals(associate2.getLastName(), associate.getLastName());
        assertEquals(associate2.getEmployeeID(), associate.getEmployeeID());
    }

}