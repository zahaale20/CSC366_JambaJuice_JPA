package csc366.jpademo;

import csc366.jpademo.employees.Director;
import csc366.jpademo.employees.DirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DirectorTests {

    @Autowired
    private DirectorRepository directorRepository;

    private Director director;

    @BeforeEach
    public void setup() {
        director = new Director("Boss", "Big", "Baby", "1234567890", "bossbaby@ceo.com", "Owner", new Date(), null, null, "Full-time", new Date(), "123-45-6789", 100000.00, "Male", "Not Specified", "Chief Executive Officer", null);
        directorRepository.deleteAll();
    }

    @Test
    public void testFindByPosition() {
        Director savedDirector = directorRepository.save(director);
        Director foundDirector = directorRepository.findByPosition(director.getPosition());
        assertNotNull(foundDirector);
        assertEquals(savedDirector.getEmployeeID(), foundDirector.getEmployeeID());
        assertEquals(savedDirector.getPosition(), foundDirector.getPosition());
    }
}