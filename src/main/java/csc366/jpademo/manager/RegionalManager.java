package csc366.jpademo;

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
    name = "regional_manager",     // may be omitted for default table naming
)
public class RegionalManager {
    @NotNull
    @Column(name="region")
    private String region;

    @NotNull
    @Column(name="reportingBoardID")
    private Long reportingBoardID;

    public RegionalManager() { }

    public RegionalManager(String region, int reportingBoardID)
    {
        this.region = region;
        this.reportingBoardID = reportingBoardID;
    }

    public String getRegion() { return region; }
    public Long getReportingBoardID() { return reportingBoardID; }

    public void setRegion(String region) { this.region = region; }
    public void setReportingBoardID(Long reportingBoardID) { this.reportingBoardID = reportingBoardID; }
}
