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
public class LocalManagerTests {

    private final static Logger log = LoggerFactory.getLogger(LocalManagerTests.class);

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private LocalManagerRepository localManagerRepository;

    @Autowired
    private RegionalManagerRepository regionalManagerRepository;

    private final Associate associate = new Associate();  // "reference" Associate
    private final LocalManager localManager = new LocalManager();

    private final RegionalManager regionalManager = new RegionalManager();

    private void regionalManagerSetUp() {
        regionalManager.setFirstName("Regional");
        regionalManager.setLastName("Manager1");
        regionalManager.setPhoneNumber("999-999-9999");
        regionalManager.setEmail("RegionalManager1@calpoly.edu");
        regionalManager.setJobTitle("Regional Manager");
        regionalManager.setDateStart(new Date());
        regionalManager.setEmploymentType("Full-time");
        regionalManager.setSSN("454-545-4545");
        regionalManager.setDateOfBirth(new Date());
        regionalManager.setSalary(200000.00);
        regionalManager.setGender("Male");
        regionalManager.setEthnicity("White");
    }

    private void localManagerSetUp() {
        localManager.setFirstName("Local");
        localManager.setLastName("Manager1");
        localManager.setPhoneNumber("222-222-2222");
        localManager.setEmail("LocalManager1@yahoo.com");
        localManager.setJobTitle("Store Manager");
        localManager.setDateStart(new Date());
        localManager.setEmploymentType("Full-time");
        localManager.setSSN("987-654-3210");
        localManager.setDateOfBirth(new Date());
        localManager.setSalary(120000.00);
        localManager.setGender("Female");
        localManager.setEthnicity("Asian");
//        localManager.setRegionalManager(regionalManager);

    }
    private void associateSetUp() {
        associate.setFirstName("Associate");
        associate.setLastName("1");
        associate.setPhoneNumber("888-888-8888");
        associate.setEmail("associate1@gmail.com");
        associate.setJobTitle("Server");
        associate.setDateStart(new Date());
        associate.setEmploymentType("Part-time");
        associate.setSSN("123-45-6789");
        associate.setDateOfBirth(new Date());
        associate.setSalary(100000.00);
        associate.setGender("Female");
        associate.setEthnicity("Hispanic");
        associate.addLocalManager(localManager);
    }

    @BeforeEach
    private void setup() {
        regionalManagerSetUp();
        localManagerSetUp();
        associateSetUp();

//        regionalManagerRepository.saveAndFlush(regionalManager);
        localManagerRepository.saveAndFlush(localManager);
        associateRepository.saveAndFlush(associate);
    }

    @Test
    @Order(1)
    public void testGetLocalManagerByAssociate() {
        LocalManager localManager2 = localManagerRepository.findByAssociate(associate).get(0);

        assertEquals(localManager.getEmployeeID(), localManager2.getEmployeeID());
    }

//    @Test
//    @Order(2)
//    public void testGetLocalManagerByRegionalManager() {
//        LocalManager localManager2 = localManagerRepository.findByRegionalManager(regionalManager).get(0);
//        assertEquals(true, true);
//        assertEquals(localManager2.getLocalManagerID(), localManager.getLocalManagerID());
//    }

}