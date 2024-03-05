package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.*;

import csc366.jpademo.employees.BoardOfDirectors;
import csc366.jpademo.employees.BoardOfDirectorsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BoardOfDirectorsTests {

    @Autowired
    private BoardOfDirectorsRepository boardOfDirectorsRepository;

    @Test
    public void testSaveBoardOfDirectors() {
        BoardOfDirectors board = new BoardOfDirectors();
        board.setBoardName("Advisory Board");
        board.setDateStart(new Date());
        board.setDateEnd(new Date());

        board = boardOfDirectorsRepository.save(board);

        assertNotNull(board.getBoardID());
        assertEquals("Advisory Board", board.getBoardName());
    }
}