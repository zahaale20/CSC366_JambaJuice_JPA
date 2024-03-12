package csc366.jpademo.customers;

import javax.persistence.*;
import java.util.StringJoiner;


@Entity
@Table(name = "taxBracket")
public class TaxBracket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxBracketID;

    @Column(name="bracket_start",nullable = false)
    private Double bracketStart;

    @Column(name="bracket_end", nullable = false)
    private Double bracketEnd;

    //this table won't be written or changed as often as others;
    //therefore, we can get away with lazy eval
    //additionally, the state_id will never change after write
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private csc366.jpademo.customers.State state;

    public TaxBracket(Long taxBracketID, Double bracketStart, Double bracketEnd, State state) {
        this.taxBracketID = taxBracketID;
        this.bracketStart = bracketStart;
        this.bracketEnd = bracketEnd;
        this.state = state;
    }

    public Long getTaxBracketID() {
        return taxBracketID;
    }

    public void setTaxBracketID(Long taxBracketID) {
        this.taxBracketID = taxBracketID;
    }

    public Double getBracketStart() {
        return bracketStart;
    }

    public void setBracketStart(Double bracketStart) {
        this.bracketStart = bracketStart;
    }

    public Double getBracketEnd() {
        return bracketEnd;
    }

    public void setBracketEnd(Double bracketEnd) {
        this.bracketEnd = bracketEnd;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", State.class.getSimpleName() + "[", "]");
        sj.add(taxBracketID.toString()).add(bracketStart.toString()).add(bracketEnd.toString()).add(state.toString());
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxBracket that = (TaxBracket) o;

        if (!taxBracketID.equals(that.taxBracketID)) return false;
        if (!bracketStart.equals(that.bracketStart)) return false;
        if (!bracketEnd.equals(that.bracketEnd)) return false;
        return state.equals(that.state);
    }

    @Override
    public int hashCode() {
        int result = taxBracketID.hashCode();
        result = 31 * result + bracketStart.hashCode();
        result = 31 * result + bracketEnd.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }
}