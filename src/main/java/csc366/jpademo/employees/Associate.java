package csc366.jpademo.employees;


import jdk.vm.ci.meta.Local;
import org.hibernate.annotations.Fetch;

import java.util.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
public class Associate extends Employee {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "associate_local_manager",
            joinColumns = @JoinColumn(name = "associate_employee_id"),
            inverseJoinColumns = @JoinColumn(name = "local_manager_id")
    )
    private Set<LocalManager> localManagers;

    public Associate() {
        this.localManagers = new HashSet<>();
    }

    public Set<LocalManager> getLocalManagers() {
        return localManagers;
    }

    public void addLocalManager(LocalManager localManager) {
        localManagers.add(localManager);
        localManager.getAssociates().add(this);
    }

    public void removeLocalManager(LocalManager localManager) {
        localManagers.remove(localManager);
        localManager.getAssociates().remove(this);
    }

    public String AssociatetoString() {
        return this.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Associate)) return false;
        return this.getEmployeeID() != null && this.getEmployeeID().equals(((Associate) o).getEmployeeID());
    }
}