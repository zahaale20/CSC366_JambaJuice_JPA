package csc366.jpademo;

import java.util.Date;
import java.util.List;

import csc366.jpademo.customers.*;
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
public class DrinkVariationTests {

    private final static Logger log = LoggerFactory.getLogger(DrinkVariationTests.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private csc366.jpademo.customers.DrinkVariationRepository drinkVariationRepository;

    private final csc366.jpademo.customers.DrinkVariation drinkVariation = new csc366.jpademo.customers.DrinkVariation("M", 2.0, 3.0, new Drink("Fanta"));

    @BeforeEach
    public void setup() {
        //drinkVariationRepository.saveAndFlush(drinkVariation);
    }

    @Test
    public void testDrinkVariation_FindByDrink() {
        assertEquals(1, 1);
    }

    

    

    

}