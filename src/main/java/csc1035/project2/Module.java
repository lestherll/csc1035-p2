package csc1035.project2;

import csc1035.project2.io.ModelsIO;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Module")
@NamedQueries({
        @NamedQuery(name = "Module_getAll", query = "FROM Module")
})
public class Module {

    @Id
    @Column(name = "module_id")
    private String moduleId;

    @Column(name = "module_name")
    private String name;

    @Column(name = "credits")
    private int credits;

    @Column(name = "weeks")
    private int weeks;

    @OneToMany(mappedBy = "module")
    private List<Booking> booking;

    public Module() {}

    public Module(String moduleId, String name, int credits, int weeks) {
        this.moduleId = moduleId;
        this.name = name;
        this.credits = credits;
        this.weeks = weeks;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }

    // Query Methods
    public static List<Module> getAll(Session session) {
        return session
                .createNamedQuery("Module_getAll", Module.class)
                .getResultList();
    }
}


