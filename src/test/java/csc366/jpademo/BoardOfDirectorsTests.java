package csc366.jpademo;

import csc366.jpademo.employees.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BoardOfDirectorsTests {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private BoardOfDirectorsRepository boardOfDirectorsRepository;

    @BeforeEach
    public void setup() {
        ownerRepository.deleteAll();
        directorRepository.deleteAll();
        boardOfDirectorsRepository.deleteAll();
    }
}
