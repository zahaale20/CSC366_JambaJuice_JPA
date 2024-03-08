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

@Entity  // indicates that this class maps to a database table
@Table(
    name = "associate"     // may be omitted for default table naming
)
public class Associate extends Employee {
    @NotNull
    @Column(name="localManagerID")
    private Long localManagerID;

    public Associate() { }

    public Associate(Long localManagerID)
    {
        this.localManagerID = localManagerID;
    }

    public Long getLocalManagerID() { return localManagerID; }

    public void setLocalManagerID(Long localManagerID) { this.localManagerID = localManagerID; }
}