package csc366.jpademo.employees;

import csc366.jpademo.Restaurant;

import javax.persistence.*;
import java.util.Date;

@Entity  // indicates that this class maps to a database table
@Table(
    name = "local_manager"     // may be omitted for default table naming
)
public class LocalManager extends Employee {
//    @NotNull
//    @Column(name="restaurantID")
//    private Long restaurantID;

//    @NotNull
    @Column(name="regionalManagerID")
    private Long regionalManagerID;

    // There can exists a local manager of no restaurant
    @ManyToOne(
            targetEntity = Restaurant.class,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    //public LocalManager() {}

    public LocalManager(String firstName, String middleName, String lastName, String phoneNumber, String email, String jobTitle, Date dateStart, Date dateEnd, Employee supervisor, String employmentType, Date dateOfBirth, String SSN, Double salary, String gender, String ethnicity, Restaurant restaurant, Long regionalManagerID)
    {
        super(firstName, middleName, lastName, phoneNumber, email, jobTitle, dateStart, dateEnd, supervisor, employmentType, dateOfBirth, SSN, salary, gender, ethnicity);
        this.restaurant = restaurant;
        this.regionalManagerID = regionalManagerID;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
//    public Long getRestaurantID() { return restaurantID; }
    public Long getRegionalManagerID() { return regionalManagerID; }

//    public void setRestaurantID(Long restaurantID) { this.restaurantID = restaurantID; }
    public void setRegionalManagerID(Long regionalManagerID) { this.regionalManagerID = regionalManagerID; }
}