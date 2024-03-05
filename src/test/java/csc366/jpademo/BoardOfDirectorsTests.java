package csc366.jpademo;

import csc366.jpademo.employees.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardOfDirectorsTests {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private BoardOfDirectorsRepository boardOfDirectorsRepository;

    private BoardOfDirectors board;
    private Owner owner;
    private Director director;

    @BeforeEach
    public void setup() {
        ownerRepository.deleteAll();
        directorRepository.deleteAll();
        boardOfDirectorsRepository.deleteAll();

        board = new BoardOfDirectors();
        board.setBoardName("Innovative Solutions Board");
        board.setDateStart(new Date());

        Long bid = board.getBoardID();

        owner = new Owner("Billy", "Edward", "Goat", "555-555-5555", "billygoat@email.com", "Dish Washer", new Date(), null, null, "Full-Time", new Date(), "123-45-6789", 10000000000.00, "Male", "White", 10.0, bid);
        owner.setBoardOfDirectors(board);

        director = new Director("Boss", "Big", "Baby", "1234567890", "bossbaby@ceo.com", "Owner", new Date(), null, null, "Full-time", new Date(), "123-45-6789", 100000.00, "Male", "Not Specified", "Chief Executive Officer", bid);
        director.setBoardOfDirectors(board);

        board.setOwners(new HashSet<>(Arrays.asList(owner)));
        board.setDirectors(new HashSet<>(Arrays.asList(director)));
    }

    @Test
    public void testSaveBoard() {
        BoardOfDirectors savedBoard = boardOfDirectorsRepository.save(board);
        assertNotNull(savedBoard);
        assertNotNull(savedBoard.getBoardID());
        assertEquals("Innovative Solutions Board", savedBoard.getBoardName());
    }

    @Test
    public void testFindBoardByName() {
        boardOfDirectorsRepository.save(board);
        BoardOfDirectors foundBoard = boardOfDirectorsRepository.findByBoardName("Innovative Solutions Board");
        assertNotNull(foundBoard);
        assertEquals(board.getBoardName(), foundBoard.getBoardName());
    }
}