package csc366.jpademo.supplier;

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
public class TransactionTest {

	private final static Logger log = LoggerFactory.getLogger(TransactionTest.class);

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private SupplyContractRepository supplyContractRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	private final Supplier supplier = new Supplier("Bob", "Last", "1234567890", "blast@calpoly.edu");
	private final SupplyContract supplyContract = new SupplyContract(10.99, "some description");
	private final Transaction transaction = new Transaction(5, "some notes");
	private final Ingredient ingredient = new Ingredient("Cheese", 21, "you need some milk");

	@BeforeEach
	private void setup() {
		transactionRepository.saveAndFlush(transaction);

		transaction.addIngredient(ingredient);
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
		assertEquals(result.getIngredients().size(), 1);
		assertNotNull(result.getSupplyContract());
		assertNotNull(result.getSupplier());
	}

	@Test
	@Order(2)
	public void testRemoveIngredient() {
		Transaction transactionResult = transactionRepository.findBySupplierName("Bob");
		Ingredient ingredientResult = ingredientRepository.findByName("Cheese");

		transactionResult.removeIngredient(ingredientResult);
		transactionRepository.save(transactionResult);
		log.info(transactionResult.toString());

		Transaction afterRemove = transactionRepository.findBySupplierName("Bob");
		assertEquals(afterRemove.getIngredients().size(), 0);
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
}
