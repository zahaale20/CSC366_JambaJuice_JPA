package csc366.jpademo.employees;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OrderColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity  // indicates that this class maps to a database table
@Table(
    name = "regional_manager"     // may be omitted for default table naming
)
public class RegionalManager extends Employee {
    @NotNull
    @Column(name="region")
    private String region;

    @NotNull
    @Column(name="reportingBoardID")
    private Long reportingBoardID;

    //public RegionalManager() { }

    public RegionalManager(String firstName, String middleName, String lastName, String phoneNumber, String email, String jobTitle, Date dateStart, Date dateEnd, Employee supervisor, String employmentType, Date dateOfBirth, String SSN, Double salary, String gender, String ethnicity, String region, Long reportingBoardID)
    {
        super(firstName, middleName, lastName, phoneNumber, email, jobTitle, dateStart, dateEnd, supervisor, employmentType, dateOfBirth, SSN, salary, gender, ethnicity);
        this.region = region;
        this.reportingBoardID = reportingBoardID;
    }

    public String getRegion() { return region; }
    public Long getReportingBoardID() { return reportingBoardID; }

    public void setRegion(String region) { this.region = region; }
    public void setReportingBoardID(Long reportingBoardID) { this.reportingBoardID = reportingBoardID; }
}