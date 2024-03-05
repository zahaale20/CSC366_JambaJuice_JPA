package csc366.jpademo.employees;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "paycheck")
public class Paycheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paycheckID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeID", nullable = false)
    private csc366.jpademo.Employee employee;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private Double wage;

    @Column(nullable = false)
    private Double hours;

    @Column(nullable = false)
    private Double tax;

    public Paycheck() {}

    public Paycheck(csc366.jpademo.Employee employee, Date date, Double wage, Double hours, Double tax) {
        this.employee = employee;
        this.date = date;
        this.wage = wage;
        this.hours = hours;
        this.tax = tax;
    }

    // Getters and setters

    public Long getPaycheckID() {
        return paycheckID;
    }

    public void setPaycheckID(Long paycheckID) {
        this.paycheckID = paycheckID;
    }

    public csc366.jpademo.Employee getEmployee() {
        return employee;
    }

    public void setEmployee(csc366.jpademo.Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Paycheck{" +
                "paycheckID=" + paycheckID +
                ", employeeID=" + employee.getEmployeeID() + // Assuming Employee has getEmployeeID()
                ", date=" + date +
                ", wage=" + wage +
                ", hours=" + hours +
                ", tax=" + tax +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paycheck)) return false;
        Paycheck paycheck = (Paycheck) o;
        return Objects.equals(paycheckID, paycheck.paycheckID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paycheckID);
    }
}