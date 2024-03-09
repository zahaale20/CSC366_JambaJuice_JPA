package csc366.jpademo.employees;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalManagerRepository extends JpaRepository<LocalManager, Long> {

}
