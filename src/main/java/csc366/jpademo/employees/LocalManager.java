package csc366.jpademo.employees;

import jdk.vm.ci.meta.Local;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import java.util.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
public class LocalManager extends Employee {
    @Column(name="localManagerID", unique = true)
    private Long localManagerID;

    @Column(name="restaurantID")
    private Long restaurantID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionalManagerID", referencedColumnName = "regionalManagerID")
    private RegionalManager regionalManager;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "associate_local_manager",
            joinColumns = @JoinColumn(name = "associate_employee_id"),
            inverseJoinColumns = @JoinColumn(name = "local_manager_id")
    )
    private Set<Associate> associates;

    public LocalManager() {
        Random random = new Random();
        this.localManagerID = random.nextLong();
        this.associates = new HashSet<>();
    }

    public LocalManager(Long restaurantID)
    {
        Random random = new Random();
        this.localManagerID = random.nextLong();
        this.restaurantID = restaurantID;
    }

    public Long getLocalManagerID() { return localManagerID; }
    public Long getRestaurantID() { return restaurantID; }
    public RegionalManager getRegionalManager() { return regionalManager; }

    public Set<Associate> getAssociates() { return associates; }


    public void setLocalManagerID(Long localManagerID) { this.localManagerID = localManagerID; }
    public void setRestaurantID(Long restaurantID) { this.restaurantID = restaurantID; }
    public void setRegionalManager(RegionalManager regionalManager) {
        if (this.regionalManager != regionalManager) {
            this.regionalManager = regionalManager;
            regionalManager.addLocalManager(this);
        }
    }
    public void unsetRegionalManager(RegionalManager regionalManager) {
        if (this.regionalManager == regionalManager) {
            this.regionalManager = null;
            regionalManager.removeLocalManager(this);
        }
    }
    public void addAssociate(Associate associate) {
        associates.add(associate);
        associate.getLocalManagers().add(this);
    }
    public void removeAssociate(Associate associate) {
        associates.remove(associate);
        associate.getLocalManagers().remove(this);
    }


    public String LocalManagertoString() {
        StringJoiner sj = new StringJoiner("," , LocalManager.class.getSimpleName() + "[" , "]");
        sj.add(localManagerID.toString()).add(restaurantID.toString());
        return this.toString() + sj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocalManager)) return false;
        return this.localManagerID != null && this.localManagerID.equals(((LocalManager) o).localManagerID);
    }

    @Override
    public int hashCode() {
        return this.localManagerID.hashCode();
    }
}