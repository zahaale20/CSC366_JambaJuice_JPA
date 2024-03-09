package csc366.jpademo;

import java.util.Date;
import java.util.List;

import csc366.jpademo.customers.Customer;
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

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;



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
public class CustomerTests {

    private final static Logger log = LoggerFactory.getLogger(CustomerTests.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private csc366.jpademo.customers.CustomerRepository customerRepository;

    private final csc366.jpademo.customers.Customer customer = new csc366.jpademo.customers.Customer("Billy Bob", "123-456-7890");

    @BeforeEach
    public void setup() {
        customerRepository.saveAndFlush(customer);
    }

    @Test
    public void testCustomer_FindByName() {
        Customer customer2 = customerRepository.findByName("Billy Bob");

        assertNotNull(customer2);
        assertEquals(customer2.getName(), customer.getName());
        assertEquals(customer2.getName(), "Billy Bob");
        assertEquals(customer2.getPhoneNumber(), customer.getPhoneNumber());
        assertEquals(customer2.getPhoneNumber(), "123-456-7890");
    }

    @Test
    public void testCustomer_FindByPhoneNumber() {
        Customer customer2 = customerRepository.findByPhoneNumber("123-456-7890");

        assertNotNull(customer2);
        assertEquals(customer2.getName(), customer.getName());
        assertEquals(customer2.getName(), "Billy Bob");
        assertEquals(customer2.getPhoneNumber(), customer.getPhoneNumber());
        assertEquals(customer2.getPhoneNumber(), "123-456-7890");
    }


    @Test
    @Transactional
    public void testCustomer_UpdateName() {
        Customer customer2 = customerRepository.findByName(customer.getName());

        String newName = "Jimmy John";
        customerRepository.updateName(customer2.getCustomerID(), newName);

        //entityManager.flush();
        entityManager.clear();  // clear cache so the Java Customer object is updated

        Customer customer2_updated = customerRepository.findByName("Jimmy John");

        log.info("cust: {}", customer.toString());
        log.info("cust2: {}", customer2.toString());
        log.info("cust2 updated: {}", customer2_updated.toString());

        assertNotNull(customer2_updated);
        assertEquals(customer2_updated.getName(), "Jimmy John");
        assertEquals(customer2_updated.getPhoneNumber(), "123-456-7890");

        assertEquals(customer2.getName(), "Billy Bob");
        assertEquals(customer2.getPhoneNumber(), "123-456-7890");
    }

    @Test
    @Transactional
    public void testCustomer_UpdatePhoneNumber() {
        Customer customer2 = customerRepository.findByName(customer.getName());

        String newPhoneNumber = "111-222-3333";
        customerRepository.updatePhoneNumber(customer2.getCustomerID(), newPhoneNumber);

        //entityManager.flush();
        entityManager.clear();  // clear cache so the Java Customer object is updated

        Customer customer2_updated = customerRepository.findByPhoneNumber("111-222-3333");

        log.info("cust: {}", customer.toString());
        log.info("cust2: {}", customer2.toString());
        log.info("cust2 updated: {}", customer2_updated.toString());

        assertNotNull(customer2_updated);
        assertEquals(customer2_updated.getName(), "Billy Bob");
        assertEquals(customer2_updated.getPhoneNumber(), "111-222-3333");

        assertEquals(customer2.getName(), "Billy Bob");
        assertEquals(customer2.getPhoneNumber(), "123-456-7890");
    }


    @Test
    @Transactional
    public void testCustomer_DeleteByName() {
        Customer customer2 = customerRepository.findByName(customer.getName());

        customerRepository.deleteByName(customer.getName());

        Customer customer3 = customerRepository.findByName(customer.getName());
        
        assertNotNull(customer2);
        assertNull(customer3);
    }

    @Test
    @Transactional
    public void testCustomer_DeleteByPhoneNumber() {
        Customer customer2 = customerRepository.findByName(customer.getName());

        customerRepository.deleteByPhoneNumber(customer.getPhoneNumber());

        Customer customer3 = customerRepository.findByName(customer.getName());
        
        assertNotNull(customer2);
        assertNull(customer3);
    }

    

}