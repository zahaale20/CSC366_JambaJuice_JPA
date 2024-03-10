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
public class StateTests {

    private final static Logger log = LoggerFactory.getLogger(StateTests.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private csc366.jpademo.customers.StateRepository stateRepository;

    private final csc366.jpademo.customers.State state = new csc366.jpademo.customers.State("CA", "California", 10.0);

    @BeforeEach
    public void setup() {
        stateRepository.saveAndFlush(state);
    }

    @Test
    public void testState_FindByName() {
        State state2 = stateRepository.findByName("California");

        assertNotNull(state2);
        assertEquals(state2.getName(), state.getName());
        assertEquals(state2.getName(), "California");
    }

    @Test
    public void testState_FindByAbbreviation() {
        State state2 = stateRepository.findByAbbreviation("CA");

        assertNotNull(state2);
        assertEquals(state2.getAbbreviation(), state.getAbbreviation());
        assertEquals(state2.getAbbreviation(), "CA");
    }

    @Test
    public void testState_FindBySalesTaxPercent() {
        List<State> states = stateRepository.findBySalesTaxPercent(10.0);

        assertNotNull(states);
        assertEquals(states.get(0).getSalesTaxPercent(), state.getSalesTaxPercent());
        assertEquals(states.get(0).getSalesTaxPercent(), 10.0);
    }

    @Test
    public void testState_FindBySalesTaxPercentRange() {
        List<State> states = stateRepository.findBySalesTaxPercentRange(9.0, 11.0);

        assertNotNull(states);
        assertEquals(states.get(0).getSalesTaxPercent(), state.getSalesTaxPercent());
        assertEquals(states.get(0).getSalesTaxPercent(), 10.0);
    }

    @Test
    public void testState_UpdateAbbreviation() {
        State state2 = stateRepository.findByAbbreviation("CA");

        String newAbbreviation = "AZ";
        stateRepository.updateAbbreviation(state.getStateID(), newAbbreviation);

        entityManager.clear();  // clear cache so the Java State object is updated

        State state3 = stateRepository.findByAbbreviation("CA");
        State state4 = stateRepository.findByAbbreviation("AZ");

        assertNotNull(state2);
        assertNull(state3);
        assertNotNull(state4);
        assertEquals(state2.getAbbreviation(), "CA");
        assertEquals(state4.getAbbreviation(), "AZ");
    }

    @Test
    public void testState_UpdateName() {
        State state2 = stateRepository.findByName("California");

        String newName = "Arizona";
        stateRepository.updateName(state.getStateID(), newName);

        entityManager.clear();  // clear cache so the Java State object is updated

        State state3 = stateRepository.findByName("California");
        State state4 = stateRepository.findByName("Arizona");

        assertNotNull(state2);
        assertNull(state3);
        assertNotNull(state4);
        assertEquals(state2.getName(), "California");
        assertEquals(state4.getName(), "Arizona");
    }

    @Test
    public void testState_UpdateSalesTaxPercent() {
        List<State> states2 = stateRepository.findBySalesTaxPercent(10.0);

        Double newSalesTaxPercent = 11.0;
        stateRepository.updateSalesTaxPercent(state.getStateID(), newSalesTaxPercent);

        entityManager.clear();  // clear cache so the Java State object is updated

        List<State> states3 = stateRepository.findBySalesTaxPercent(10.0);
        List<State> states4 = stateRepository.findBySalesTaxPercent(11.0);

        assertNotNull(states2.get(0));
        assertEquals(states3.size(), 0);
        assertNotNull(states4.get(0));
        assertEquals(states2.get(0).getSalesTaxPercent(), 10.0);
        assertEquals(states4.get(0).getSalesTaxPercent(), 11.0);
    }


    @Test
    public void testState_DeleteByAbbreviation() {
        State state2 = stateRepository.findByAbbreviation("CA");

        stateRepository.deleteByAbbreviation("CA");

        State state3 = stateRepository.findByAbbreviation("CA");

        assertNotNull(state2);
        assertNull(state3);
        assertEquals(state2.getAbbreviation(), "CA");
    }

    @Test
    public void testState_DeleteByName() {
        State state2 = stateRepository.findByName("California");

        stateRepository.deleteByName("California");

        State state3 = stateRepository.findByName("California");

        assertNotNull(state2);
        assertNull(state3);
        assertEquals(state2.getName(), "California");
    }

    

    

    

}