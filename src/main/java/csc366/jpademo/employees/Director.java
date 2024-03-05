package csc366.jpademo.employees;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("DIRECTOR")
public class Director extends Employee {
    @Column(name = "position", nullable = true)
    private String position;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = true)
    private BoardOfDirectors boardOfDirectors;

    public Director(String firstName, String middleName, String lastName, String phoneNumber, String email, String jobTitle, Date dateStart, Date dateEnd, Employee supervisor, String employmentType, Date dateOfBirth, String SSN, Double salary, String gender, String ethnicity, String position) {
        super(firstName, middleName, lastName, phoneNumber, email, jobTitle, dateStart, dateEnd, supervisor, employmentType, dateOfBirth, SSN, salary, gender, ethnicity);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBoardOfDirectors(BoardOfDirectors board) {
    }
}