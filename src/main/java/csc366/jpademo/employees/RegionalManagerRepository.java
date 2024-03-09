package csc366.jpademo.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegionalManagerRepository extends JpaRepository<RegionalManager, Long> {
    @Query("SELECT rm FROM RegionalManager rm WHERE :localManager MEMBER OF rm.localManagers")
    RegionalManager findByLocalManager(@Param("localManager") LocalManager localManager);

    @Query("SELECT rm FROM RegionalManager rm WHERE rm.boardOfDirectors = :board")
    RegionalManager findByBoard(@Param("board") BoardOfDirectors board);
}
