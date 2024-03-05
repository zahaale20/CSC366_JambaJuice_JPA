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
    name = "local_manager",     // may be omitted for default table naming
)
public class LocalManager {
    @NotNull
    @Column(name="restaurantID")
    private Long restaurantID;

    @NotNull
    @Column(name="regionalManagerID")
    private Long regionalManagerID;

    public LocalManager() { }

    public LocalManager(Long restaurantID, Long regionalManagerID)
    {
        this.restaurantID = restaurantID;
        this.regionalManagerID = regionalManagerID;
    }

    public Long getRestaurantID() { return restaurantID; }
    public Long getRegionalManagerID() { return regionalManagerID; }

    public void setRestaurantID(Long restaurantID) { this.restaurantID = restaurantID; }
    public void setRegionalManagerID(Long regionalManagerID) { this.regionalManagerID = regionalManagerID; }
}
