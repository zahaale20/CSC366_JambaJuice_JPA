//IMPORT Employee class
package csc366.jpademo.employees;

import java.util.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
public class RegionalManager extends Employee implements java.io.Serializable {
    @Column(name = "id")
    private Long id;

    @Column(name="region")
    private String region;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "boardID")
    private BoardOfDirectors boardOfDirectors;

    @OneToMany(mappedBy = "regionalManager", fetch = FetchType.LAZY)
    private Set<LocalManager> localManagers;

    public RegionalManager() {
        Random random = new Random();
        this.id = random.nextLong();
        this.localManagers = new HashSet<>();
    }

    public RegionalManager(String region)
    {
        Random random = new Random();
        this.id = random.nextLong();
        this.region = region;
        this.localManagers = new HashSet<>();
    }

    public Long getid() { return id; }
    public String getRegion() { return region; }
    public BoardOfDirectors getReportingBoard() { return boardOfDirectors; }
    public Set<LocalManager> getLocalManagers() {
        return localManagers;
    }

    public void setid(Long id) { this.id = id; }
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
        sj.add(this.id.toString()).add(region);
        return this.toString() + sj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegionalManager)) return false;
        return this.id != null && this.id.equals(((RegionalManager) o).id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}