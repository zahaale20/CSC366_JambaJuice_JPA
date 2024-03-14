package csc366.jpademo.supplier;

import csc366.jpademo.Restaurant;
import csc366.jpademo.RestaurantRepository;
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
import static org.mockito.ArgumentMatchers.any;

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
public class TransactionTest {

	private final static Logger log = LoggerFactory.getLogger(TransactionTest.class);

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private SupplyContractRepository supplyContractRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private AssociateRepository associateRepository;

	@Autowired
	private LocalManagerRepository localManagerRepository;

	@Autowired
	private RegionalManagerRepository regionalManagerRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerReceiptRepository customerReceiptRepository;

//	@

	private Associate associate = new Associate();  // "reference" Associate
	private  LocalManager localManager = new LocalManager();

	private  RegionalManager regionalManager = new RegionalManager();

	private final Customer customer = new Customer("Bob Jones", "1234567890");
	private State state = new State("CA", "California", 99.99);
	private CustomerReceipt customerReceipt = new CustomerReceipt(20.00, new Date(), 15.0, customer, state);

	private final Restaurant restaurant = new Restaurant("2 Grand Ave", state,localManager, customerReceipt);
	private final Supplier supplier = new Supplier("Bob", "Last", "1234567890", "blast@calpoly.edu");
	private final SupplyContract supplyContract = new SupplyContract(10.99, "some description");
	private final Transaction transaction = new Transaction(5, "some notes");
	private final Ingredient ingredient = new Ingredient("Cheese", 21, "you need some milk");

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
		localManager.setRegionalManager(any());
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

	@BeforeEach
	private void setup() {
		regionalManagerSetUp();
		localManagerSetUp();
		associateSetUp();

		regionalManager =  regionalManagerRepository.saveAndFlush(regionalManager);
		localManager =  localManagerRepository.saveAndFlush(localManager);
		associate =  associateRepository.saveAndFlush(associate);

		stateRepository.saveAndFlush(state);
		customerRepository.saveAndFlush(customer);
		customerReceiptRepository.saveAndFlush(customerReceipt);
		restaurantRepository.saveAndFlush(restaurant);

		transactionRepository.saveAndFlush(transaction);

		transaction.setIngredient(ingredient);
		ingredientRepository.saveAndFlush(ingredient);

		transaction.addSupplier(supplier);
		supplierRepository.saveAndFlush(supplier);

		transaction.addSupplyContract(supplyContract);
		supplyContractRepository.saveAndFlush(supplyContract);
	}

	@Test
	@Order(1)
	public void testTransactionReferences() {
		Transaction result = transactionRepository.findBySupplierName("Bob");

		log.info(result.toString());

		assertNotNull(result);
		assertNotNull(result.getIngredient());
		assertNotNull(result.getSupplyContract());
		assertNotNull(result.getSupplier());
	}

	@Test
	@Order(2)
	public void testSetIngredient() {
		Transaction transactionResult = transactionRepository.findBySupplierName("Bob");
		Ingredient ingredientResult = ingredientRepository.findByName("Cheese");

		transactionResult.setIngredient(ingredientResult);
		transactionRepository.save(transactionResult);
		log.info(transactionResult.toString());

		Transaction afterRemove = transactionRepository.findBySupplierName("Bob");
		assertEquals(afterRemove.getIngredient(), ingredientResult);
	}

	@Test
	@Order(3)
	public void testRemoveSupplyContract() {
		Transaction transactionResult = transactionRepository.findBySupplierName("Bob");
		SupplyContract supplyContractResult = transactionResult.getSupplyContract();

		transactionResult.removeSupplyContract(supplyContractResult);
		transactionRepository.save(transactionResult);
		log.info(transactionResult.toString());
	}

	@Test
	@Order(3)
	public void testRemoveSupplier() {
		Transaction transactionResult = transactionRepository.findBySupplierName("Bob");
		Supplier supplierResult = transactionResult.getSupplier();

		transactionResult.removeSupplier(supplierResult);
		transactionRepository.save(transactionResult);
		log.info(transactionResult.toString());
	}

	@Test
	@Order(4)
	public void testSetRestaurant() {
		Transaction transactionResult = transactionRepository.findBySupplierName("Bob");

		assertNull(transactionResult.getRestaurant());

		transactionResult.setResturant(restaurant);
		transactionRepository.save(transactionResult);
		log.info(transactionResult.toString());

		Transaction afterRemove = transactionRepository.findByRestaurantAddress("2 Grand Ave");
		assertEquals(afterRemove.getRestaurant(), restaurant);
	}
}
