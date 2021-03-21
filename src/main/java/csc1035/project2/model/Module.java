package csc1035.project2.model;
import org.hibernate.Session;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is the module class when booking a room there needs to be a module that the room is booked for
 * The module class contains all need information about the ID, Name, Duration and Credits awarded for the module.
 *
 * @auther
 * JavaDoc and comments by Ben Mitchell
 *
 *
 * "Module_getAll" query will return all of the Modules from the Module Table
 *
 */

@Entity
@Table(name = "Module")
@NamedQueries({
        @NamedQuery(name = "Module_getAll", query = "FROM Module")
})
public class Module {
    //Create variables and assign them to the columns in the Module Table
    @Id
    @Column(name = "module_id")
    private String moduleId;

    @Column(name = "module_name")
    private String name;

    @Column(name = "credits")
    private int credits;

    @Column(name = "weeks")
    private int weeks;

    // Create all the relationships to the other tables in the database
    @OneToMany(mappedBy = "module")
    private List<Booking> booking;

    @ManyToMany(mappedBy = "modules")
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "modules")
    private Set<Staff> staff = new HashSet<>();

    public Module() {}

    /**
     *
     * @param moduleId This is the identifier for the Module
     * @param name this is the name of the Module
     * @param credits This is how many credits will be awarded for the module
     * @param weeks This is the duration that the module will last
     *
     */
    public Module(String moduleId, String name, int credits, int weeks) {
        this.moduleId = moduleId;
        this.name = name;
        this.credits = credits;
        this.weeks = weeks;
    }

    //Create all of the get and setter methods so yuo are able to set and return individual attributes
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

    /**
     *
     * @param session Creates Database Session
     * @return returns all of the modules in the module Table
     */

    public static List<Module> getAll(Session session) {
        return session
                .createNamedQuery("Module_getAll", Module.class)
                .getResultList();
    }
}


