package csc366.jpademo.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardOfDirectorsRepository extends JpaRepository<BoardOfDirectors, Long> {

    BoardOfDirectors findByBoardName(String position);
}
