//IMPORT Employee class
package csc366.jpademo.employees;

import java.util.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
public class RegionalManager extends Employee {
    @NotNull
    @Column(name = "regionalManagerID", unique = true)
    private Long regionalManagerID;

    @Column(name="region")
    private String region;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "boardID")
    private BoardOfDirectors boardOfDirectors;

    @OneToMany(mappedBy = "regionalManager", fetch = FetchType.LAZY)
    private Set<LocalManager> localManagers;

    public RegionalManager() {
        Random random = new Random();
        this.regionalManagerID = random.nextLong();
        this.localManagers = new HashSet<>();
    }

    public RegionalManager(String region)
    {
        Random random = new Random();
        this.regionalManagerID = random.nextLong();
        this.region = region;
        this.localManagers = new HashSet<>();
    }

    public Long getRegionalManagerID() { return regionalManagerID; }
    public String getRegion() { return region; }
    public BoardOfDirectors getReportingBoard() { return boardOfDirectors; }
    public Set<LocalManager> getLocalManagers() {
        return localManagers;
    }

    public void setRegionalManagerID(Long regionalManagerID) { this.regionalManagerID = regionalManagerID; }
    public void setRegion(String region) { this.region = region; }
    public void setReportingBoard(BoardOfDirectors boardOfDirectors) { this.boardOfDirectors = boardOfDirectors; }
    public void addLocalManager(LocalManager localManager) {
        if (!localManagers.contains(localManager)) {
            localManagers.add(localManager);
            localManager.setRegionalManager(this);
        }
    }
    public void removeLocalManager(LocalManager localManager) {
        if (localManagers.contains(localManager)) {
            localManagers.remove(localManager);
            localManager.unsetRegionalManager(this);
        }
    }

    public String RegionalManagertoString() {
        StringJoiner sj = new StringJoiner("," , RegionalManager.class.getSimpleName() + "[" , "]");
        sj.add(this.regionalManagerID.toString()).add(region);
        return this.toString() + sj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegionalManager)) return false;
        return this.regionalManagerID != null && this.regionalManagerID.equals(((RegionalManager) o).regionalManagerID);
    }

    @Override
    public int hashCode() {
        return this.regionalManagerID.hashCode();
    }
}