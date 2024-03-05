package csc366.jpademo.employees;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "leave")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeID", nullable = false)
    private csc366.jpademo.employees.Employee employee;

    @Column(nullable = false)
    private String type;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "approval_status", nullable = false)
    private String approvalStatus;

    @Column(nullable = false)
    private String reason;

    public Leave() {}

    public Leave(csc366.jpademo.employees.Employee employee, String type, Date startDate, Date endDate, String approvalStatus, String reason) {
        this.employee = employee;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approvalStatus = approvalStatus;
        this.reason = reason;
    }

    // Getters and setters
    public Long getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(Long leaveID) {
        this.leaveID = leaveID;
    }

    public csc366.jpademo.employees.Employee getEmployee() {
        return employee;
    }

    public void setEmployee(csc366.jpademo.employees.Employee employee) {
        this.employee = employee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "leaveID=" + leaveID +
                ", employeeID=" + (employee != null ? employee.getEmployeeID() : null) +
                ", type='" + type + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Leave leave = (Leave) o;

        if (!Objects.equals(leaveID, leave.leaveID)) return false;
        return Objects.equals(employee != null ? employee.getEmployeeID() : null, leave.employee != null ? leave.employee.getEmployeeID() : null);
    }

    @Override
    public int hashCode() {
        int result = leaveID != null ? leaveID.hashCode() : 0;
        result = 31 * result + (employee != null && employee.getEmployeeID() != null ? employee.getEmployeeID().hashCode() : 0);
        return result;
    }
}