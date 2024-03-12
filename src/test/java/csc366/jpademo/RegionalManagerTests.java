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
public class RegionalManagerTests {

    private final static Logger log = LoggerFactory.getLogger(RegionalManagerTests.class);


    @Autowired
    private LocalManagerRepository localManagerRepository;

    @Autowired
    private RegionalManagerRepository regionalManagerRepository;

    @Autowired
    private BoardOfDirectorsRepository boardOfDirectorsRepository;

    private final LocalManager localManager = new LocalManager();

    private final RegionalManager regionalManager = new RegionalManager();
    private final BoardOfDirectors boardOfDirectors = new BoardOfDirectors();

    private void boardOfDirectorsSetUp() {
        boardOfDirectors.setBoardName("my-board");
        boardOfDirectors.setDateStart(new Date());
    }

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
        regionalManager.setReportingBoard(boardOfDirectors);
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
        localManager.setRegionalManager(regionalManager);
    }

    @BeforeEach
    private void setup() {
        localManagerSetUp();
        regionalManagerSetUp();
        boardOfDirectorsSetUp();

        boardOfDirectorsRepository.saveAndFlush(boardOfDirectors);
        regionalManagerRepository.saveAndFlush(regionalManager);
        localManagerRepository.saveAndFlush(localManager);

    }

    @Test
    @Order(1)
    public void testGetByLocalManager() {
        RegionalManager regionalManager2 = regionalManagerRepository.findByLocalManager(localManager);

        assertEquals(regionalManager2.getEmployeeID(), regionalManager.getEmployeeID());
    }

    @Test
    @Order(2)
    public void testGetByBoard() {
        RegionalManager regionalManager2 = regionalManagerRepository.findByBoard(boardOfDirectors);

        assertEquals(regionalManager2.getEmployeeID(), regionalManager.getEmployeeID());
    }

}