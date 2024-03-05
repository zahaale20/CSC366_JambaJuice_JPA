package csc366.jpademo.employees;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BoardOfDirectors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardID;

    @Column(name = "board_name", nullable = false)
    private String boardName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end", nullable = true)
    private Date dateEnd;

    // Getters
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
}