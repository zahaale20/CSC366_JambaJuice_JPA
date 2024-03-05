package csc366.jpademo;

import csc366.jpademo.employees.Owner;
import csc366.jpademo.employees.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OwnerTests {

    @Autowired
    private OwnerRepository ownerRepository;

    private Owner owner;

    @BeforeEach
    public void setup() {
        owner = new Owner("Billy", "Edward", "Goat", "555-555-5555", "billygoat@email.com", "Dish Washer", new Date(), null, null, "Full-Time", new Date(), "123-45-6789", 10000000000.00, "Male", "White", 10.0, null);
        ownerRepository.deleteAll();
    }

    @Test
    public void testSaveOwner() {
        Owner savedOwner = ownerRepository.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getEmployeeID());
        assertEquals(owner.getEmail(), savedOwner.getEmail());
    }

    @Test
    public void testFindOwnersWithSalaryGreaterThan() {
        ownerRepository.save(owner);
        List<Owner> owners = ownerRepository.findOwnersWithSalaryGreaterThan(9999999999.99);
        assertFalse(owners.isEmpty());
        assertTrue(owners.stream().anyMatch(o -> o.getEmail().equals(owner.getEmail())));
    }

    @Test
    public void testUpdateOwner() {
        Owner savedOwner = ownerRepository.save(owner);
        savedOwner.setJobTitle("CEO");
        ownerRepository.save(savedOwner);

        Owner updatedOwner = ownerRepository.findById(savedOwner.getEmployeeID()).orElse(null);
        assertNotNull(updatedOwner);
        assertEquals("CEO", updatedOwner.getJobTitle());
    }

    @Test
    public void testDeleteOwner() {
        Owner savedOwner = ownerRepository.save(owner);
        Long id = savedOwner.getEmployeeID();
        ownerRepository.delete(savedOwner);

        assertFalse(ownerRepository.findById(id).isPresent());
    }

    @Test
    public void testOwnerWithoutOwnershipPercentage() {
        owner.setOwnershipPercentage(null);
        Owner savedOwner = ownerRepository.save(owner);

        Owner foundOwner = ownerRepository.findById(savedOwner.getEmployeeID()).orElse(null);
        assertNotNull(foundOwner);
        assertNull(foundOwner.getOwnershipPercentage(), "Ownership percentage should be null.");
    }

    @Test
    public void testOwnerWithOwnershipPercentage() {
        owner.setOwnershipPercentage(11.1);
        Owner savedOwner = ownerRepository.save(owner);
        Owner foundOwner = ownerRepository.findById(savedOwner.getEmployeeID()).orElse(null);
        assertEquals(foundOwner.getOwnershipPercentage(), 11.1);
    }

    @Test
    public void testUpdateOwnershipPercentage() {
        Owner savedOwner = ownerRepository.save(owner);
        savedOwner.setOwnershipPercentage(15.0);
        ownerRepository.save(savedOwner);

        Owner updatedOwner = ownerRepository.findById(savedOwner.getEmployeeID()).orElse(null);
        assertNotNull(updatedOwner);
        assertEquals(15.0, updatedOwner.getOwnershipPercentage(), "Ownership percentage should be updated to 15.0");
    }

}
