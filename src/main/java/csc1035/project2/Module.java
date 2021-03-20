package csc1035.project2;

import csc1035.project2.io.ModelsIO;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(mappedBy = "modules")
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "modules")
    private Set<Staff> staff = new HashSet<>();

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

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    // Query Methods
    public static List<Module> getAll(Session session) {
        return session
                .createNamedQuery("Module_getAll", Module.class)
                .getResultList();
    }
}


