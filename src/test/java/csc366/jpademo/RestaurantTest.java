package csc366.jpademo;

import csc366.jpademo.customers.*;
import csc366.jpademo.employees.*;
import csc366.jpademo.supplier.Ingredient;
import csc366.jpademo.supplier.IngredientRepository;
import csc366.jpademo.supplier.Transaction;
import csc366.jpademo.supplier.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
	"spring.main.banner-mode=off",
	"logging.level.root=ERROR",
	"logging.level.csc366=DEBUG",

	"spring.jpa.hibernate.ddl-auto=update",
	"spring.datasource.url=jdbc:mysql://mysql.labthreesixsix.com/csc366",
	"spring.datasource.username=jpa",
	"spring.datasource.password=demo",
	
	"logging.level.org.hibernate.SQL=DEBUG",
	"logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
	"spring.jpa.properties.hibernate.format_sql=true",
	"spring.jpa.show-sql=false",   // prevent duplicate logging
	"spring.jpa.properties.hibernate.show_sql=false",	
	
	"logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
public class RestaurantTest {

	private final static Logger log = LoggerFactory.getLogger(RestaurantTest.class);

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private LocalManagerRepository localManagerRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private RegionalManagerRepository regionalManagerRepository;

	@Autowired
	private CustomerReceiptRepository customerReceiptRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private final State state = new State("WA", "testName", 20.0);
	private final Customer customer = new Customer("Bob", "1234567890");
	private final CustomerReceipt customerReceipt = new CustomerReceipt(20.00, new Date(), 15.0, customer, state);
	private final Owner owner = new Owner("f", "f", "f", "12312", "ferijm", "few", new Date(), new Date(), null, "fesf", new Date(), "fews", 24.5, "oither", "other", 23.0, 0L);
	private final RegionalManager regionalManager = new RegionalManager("f", "l", "l", "987654321", "calpoly.edu", "supervisor", new Date(), new Date(), owner, "lol", new Date(), "12324", 123.0, "other", "other", "canada", 0L);
	private final LocalManager localManager = new LocalManager("Bob", "P", "Jones", "123456789", "jones@calpoly.edu", "lead trainer", new Date(), new Date(), regionalManager, "idk", new Date(), "11111111", 120000.0, "other", "everything", null, 0L);
	private final Restaurant restaurant = new Restaurant("2 Grand Ave", state, localManager, customerReceipt);
	private final LocalManager newLocalManager = new LocalManager("Non", "A", "Hack", "987654321", "make@calpoly.edu", "L", new Date(), new Date(), regionalManager, "idk", new Date(), "22222222", 120000.0, "other", "everything", restaurant, 0L);

	@BeforeEach
	private void setup() {
		localManager.setRestaurant(restaurant);

		stateRepository.saveAndFlush(state);
		customerRepository.saveAndFlush(customer);
		customerReceiptRepository.saveAndFlush(customerReceipt);
		ownerRepository.saveAndFlush(owner);
		regionalManagerRepository.saveAndFlush(regionalManager);
		localManagerRepository.saveAndFlush(localManager);

		restaurantRepository.saveAndFlush(restaurant);
	}

	@Test
	@Order(1)
	public void testRestaurantReferences() {
		Restaurant result = restaurantRepository.findByAddress("2 Grand Ave");

		assertNotNull(result);
		assertNotNull(result.getCustomerReceipt());
		assertNotNull(result.getState());
		assertEquals(result.getLocalManagers().size(), 1);
	}

//	@Test
//	@Order(2)
//	public void testAddLocalManager() {
//		Restaurant result = restaurantRepository.findByAddress("2 Grand Ave");
//
//		result.addLocalManager(newLocalManager);
//		restaurantRepository.saveAndFlush(result);
//
//		result = restaurantRepository.findByAddress("2 Grand Ave");
//		assertEquals(result.getLocalManagers().size(), 2);
//	}
//
//	@Test
//	@Order(2)
//	public void testRemoveLocalManager() {
//		Restaurant result = restaurantRepository.findByAddress("2 Grand Ave");
//		result.addLocalManager(newLocalManager);
//		restaurantRepository.saveAndFlush(result);
//
//		result.removeLocalManger(result.getLocalManagers().get(0));
//		restaurantRepository.saveAndFlush(result);
//		assertEquals(result.getLocalManagers().size(), 1);
//
//		assertThrows(ConstraintViolationException.class, () -> {
//			result.removeLocalManger(result.getLocalManagers().get(0));
//			restaurantRepository.saveAndFlush(result);
//		});
//	}
}
