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
public class IngredientTest {

	private final static Logger log = LoggerFactory.getLogger(IngredientTest.class);

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	private final Ingredient ingredient = new Ingredient("Cheese", 21, "you need some milk");
	private final Transaction transaction = new Transaction(5, "some notes");

	@BeforeEach
	private void setup() {
		ingredientRepository.saveAndFlush(ingredient);

		ingredient.addTransaction(transaction);
		transactionRepository.saveAndFlush(transaction);
	}

	@Test
	@Order(1)
	public void testIngredientReferences() {
		Ingredient result = ingredientRepository.findByName("Cheese");

		log.info(result.toString());

		assertNotNull(result);
		assertEquals(result.getTransactions().size(), 1);
	}

	@Test
	@Order(2)
	public void testRemoveTransaction() {
		Ingredient ingredientResult = ingredientRepository.findByName("Cheese");
		Transaction transactionResult = ingredientResult.getTransactions().get(0);

		ingredientResult.removeTransaction(transactionResult);
		ingredientRepository.save(ingredientResult);
		log.info(ingredientResult.toString());

		Ingredient afterRemove = ingredientRepository.findByName("Cheese");
		assertTrue(afterRemove.getTransactions().isEmpty());
	}
}
