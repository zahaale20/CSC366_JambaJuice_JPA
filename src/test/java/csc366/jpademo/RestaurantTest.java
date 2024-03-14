package csc366.jpademo;

import csc366.jpademo.customers.*;
import csc366.jpademo.employees.*;
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
	private AssociateRepository associateRepository;

	@Autowired
	private RegionalManagerRepository regionalManagerRepository;

	@Autowired
	private CustomerReceiptRepository customerReceiptRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private Associate associate = new Associate();
	private LocalManager localManager = new LocalManager();
	private RegionalManager regionalManager = new RegionalManager();
	private Associate associate2 = new Associate();
	private LocalManager localManager2 = new LocalManager();
	private RegionalManager regionalManager2 = new RegionalManager();
	private State state = new State("CA", "California", 99.99);
	private final Customer customer = new Customer("Bob Jones", "1234567890");
	private Restaurant restaurant = new Restaurant("2 Grand Ave", state, localManager);
	private CustomerReceipt customerReceipt = new CustomerReceipt(20.00, new Date(), 15.0, restaurant, customer, state);

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
		localManager.setRegionalManager(regionalManager);

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

	private void regionalManagerSetUp2() {
		regionalManager2.setFirstName("Regional2");
		regionalManager2.setLastName("Manager12");
		regionalManager2.setPhoneNumber("999-999-99992");
		regionalManager2.setEmail("RegionalManager1@calpoly.edu2");
		regionalManager2.setJobTitle("Regional Manager2");
		regionalManager2.setDateStart(new Date());
		regionalManager2.setEmploymentType("Full-time2");
		regionalManager2.setSSN("454-545-45452");
		regionalManager2.setDateOfBirth(new Date());
		regionalManager2.setSalary(200002.00);
		regionalManager2.setGender("Male2");
		regionalManager2.setEthnicity("White2");
	}

	private void localManagerSetUp2() {
		localManager2.setFirstName("Local2");
		localManager2.setLastName("Manager12");
		localManager2.setPhoneNumber("222-222-22222");
		localManager2.setEmail("LocalManager1@yahoo.com2");
		localManager2.setJobTitle("Store Manager2");
		localManager2.setDateStart(new Date());
		localManager2.setEmploymentType("Full-time2");
		localManager2.setSSN("987-654-32102");
		localManager2.setDateOfBirth(new Date());
		localManager2.setSalary(120002.00);
		localManager2.setGender("Female2");
		localManager2.setEthnicity("Asian2");
		localManager2.setRegionalManager(regionalManager);

	}
	private void associateSetUp2() {
		associate2.setFirstName("Associate2");
		associate2.setLastName("12");
		associate2.setPhoneNumber("888-888-88882");
		associate2.setEmail("associate1@gmail.com2");
		associate2.setJobTitle("Server2");
		associate2.setDateStart(new Date());
		associate2.setEmploymentType("Part-time2");
		associate2.setSSN("123-45-67892");
		associate2.setDateOfBirth(new Date());
		associate2.setSalary(100002.00);
		associate2.setGender("Female2");
		associate2.setEthnicity("Hispanic2");
		associate2.addLocalManager(localManager);
	}

	@BeforeEach
	public void setup() {
		regionalManagerSetUp();
		localManagerSetUp();
		associateSetUp();

		regionalManager = regionalManagerRepository.saveAndFlush(regionalManager);
		localManager = localManagerRepository.saveAndFlush(localManager);
		associate = associateRepository.saveAndFlush(associate);

		stateRepository.saveAndFlush(state);
		customerRepository.saveAndFlush(customer);

		restaurantRepository.saveAndFlush(restaurant);
		customerReceiptRepository.saveAndFlush(customerReceipt);
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

	@Test
	@Order(2)
	public void testAddLocalManager() {
		Restaurant result = restaurantRepository.findByAddress("2 Grand Ave");

		regionalManagerSetUp2();
		localManagerSetUp2();
		associateSetUp2();

		regionalManager2 = regionalManagerRepository.saveAndFlush(regionalManager2);
		localManager2 = localManagerRepository.saveAndFlush(localManager2);
		associate2 = associateRepository.saveAndFlush(associate2);

		result.addLocalManager(localManager2);
		restaurantRepository.saveAndFlush(result);

		result = restaurantRepository.findByAddress("2 Grand Ave");
		assertEquals(result.getLocalManagers().size(), 2);
	}

	@Test
	@Order(2)
	public void testRemoveLocalManager() {
		Restaurant result = restaurantRepository.findByAddress("2 Grand Ave");

		result.removeLocalManger(result.getLocalManagers().get(0));
		restaurantRepository.saveAndFlush(result);

		result = restaurantRepository.findByAddress("2 Grand Ave");
		assertEquals(result.getLocalManagers().size(), 0);
	}
}
