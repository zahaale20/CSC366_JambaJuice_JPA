package csc366.jpademo.employees;


import csc366.jpademo.employees.Employee;

import javax.persistence.*;
import java.util.Date;


@Entity
@DiscriminatorValue("OWNER")
public class Owner extends Employee {
    @Column(name = "ownership_percentage", nullable = true)
    private Double ownershipPercentage;

    public Owner(String firstName, String middleName, String lastName, String phoneNumber, String email, String jobTitle, Date dateStart, Date dateEnd, Employee supervisor, String employmentType, Date dateOfBirth, String SSN, Double salary, String gender, String ethnicity, Double ownershipPercentage) {
        super(firstName, middleName, lastName, phoneNumber, email, jobTitle, dateStart, dateEnd, supervisor, employmentType, dateOfBirth, SSN, salary, gender, ethnicity);
    }

    public Double getOwnershipPercentage() {
        return ownershipPercentage;
    }

    public void setOwnershipPercentage(Double ownershipPercentage) {
        this.ownershipPercentage = ownershipPercentage;
    }

}