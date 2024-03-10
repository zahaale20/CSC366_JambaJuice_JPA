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
public class SupplyContractTest {

	private final static Logger log = LoggerFactory.getLogger(SupplyContractTest.class);

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private SupplyContractRepository supplyContractRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	private final Supplier supplier = new Supplier("Bob", "Last", "1234567890", "blast@calpoly.edu");
	private final SupplyContract supplyContract = new SupplyContract(10.99, "some description");
	private final Transaction transaction = new Transaction(5, "some notes");

	@BeforeEach
	private void setup() {
		supplyContractRepository.saveAndFlush(supplyContract);

		supplyContract.addTransaction(transaction);
		transactionRepository.saveAndFlush(transaction);

		supplyContract.addSupplier(supplier);
		supplierRepository.saveAndFlush(supplier);
	}

	@Test
	@Order(1)
	public void testSupplyContractReferences() {
		SupplyContract supplyContractResult = supplyContractRepository.findBySupplierName("Bob");

		log.info(supplyContractResult.toString());

		assertNotNull(supplyContractResult);
		assertNotNull(supplyContractResult.getSupplier());
		assertNotNull(supplyContractResult.getTransaction());
	}

	@Test
	@Order(2)
	public void testRemoveSupply() {
		SupplyContract supplyContractResult = supplyContractRepository.findBySupplierName("Bob");
		Supplier supplierResult = supplierRepository.findByFirstName("Bob");

		supplyContractResult.removeSupplier(supplierResult);
		supplyContractRepository.save(supplyContractResult);
		log.info(supplyContractResult.toString());

		SupplyContract supplyContractAfterRemove = supplyContractRepository.getOne(supplyContractResult.getId());
		assertNull(supplyContractAfterRemove.getSupplier());
	}

	@Test
	@Order(3)
	public void testRemoveTransaction() {
		SupplyContract supplyContractResult = supplyContractRepository.findBySupplierName("Bob");
		Transaction transactionResult = supplyContractResult.getTransaction();

		supplyContractResult.removeTransaction(transactionResult);
		supplyContractRepository.save(supplyContractResult);
		log.info(supplyContractResult.toString());

		SupplyContract supplyContractAfterRemove = supplyContractRepository.findBySupplierName("Bob");
		assertNull(supplyContractAfterRemove.getTransaction());
	}
}
