package csc366.jpademo.employees;

import javax.persistence.*;
import javax.swing.plaf.synth.Region;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BoardOfDirectors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardID;

    @OneToMany(mappedBy = "boardOfDirectors", fetch = FetchType.EAGER) // Changed to EAGER fetching
    private Set<Owner> owners = new HashSet<>();

    @OneToMany(mappedBy = "boardOfDirectors", fetch = FetchType.LAZY)
    private Set<RegionalManager> regionalManagers = new HashSet<>();

    @OneToMany(mappedBy = "boardOfDirectors", fetch = FetchType.EAGER) // Changed to EAGER fetching
    private Set<Director> directors = new HashSet<>();

    @Column(name = "board_name", nullable = false)
    private String boardName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end", nullable = true)
    private Date dateEnd;

    public Long getBoardID() {
        return boardID;
    }

    public String getBoardName() {
        return boardName;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setBoardID(Long boardID) {
        this.boardID = boardID;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Set<Owner> getOwners() { return owners; }
    public void setOwners(Set<Owner> owners) { this.owners = owners; }

    public Set<Director> getDirectors() { return directors; }
    public void setDirectors(Set<Director> directors) { this.directors = directors; }
}