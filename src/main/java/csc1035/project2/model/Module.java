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

    @Column(name = "no_of_lectures")
    private int noOfLectures;

    @Column(name = "lecture_length")
    private int lectureLength;

    @Column(name = "no_of_practicals")
    private int noOfPracticals;

    @Column(name = "practical_length")
    private int practicalLength;

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

    public Module(String moduleId, String name, int credits, int weeks, int noOfLectures, int lectureLength, int noOfPracticals, int practicalLength) {
        this.moduleId = moduleId;
        this.name = name;
        this.credits = credits;
        this.weeks = weeks;
        this.noOfLectures = noOfLectures;
        this.lectureLength = lectureLength;
        this.noOfPracticals = noOfPracticals;
        this.practicalLength = practicalLength;
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

    public int getNoOfLectures() {
        return noOfLectures;
    }

    public void setNoOfLectures(int noOfLectures) {
        this.noOfLectures = noOfLectures;
    }

    public int getLectureLength() {
        return lectureLength;
    }

    public void setLectureLength(int lectureLength) {
        this.lectureLength = lectureLength;
    }

    public int getNoOfPracticals() {
        return noOfPracticals;
    }

    public void setNoOfPracticals(int noOfPracticals) {
        this.noOfPracticals = noOfPracticals;
    }

    public int getPracticalLength() {
        return practicalLength;
    }

    public void setPracticalLength(int practicalLength) {
        this.practicalLength = practicalLength;
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

    @Override
    public String toString() {
        return "Module{" +
                "moduleId='" + moduleId + '\'' +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", weeks=" + weeks +
                ", noOfLectures=" + noOfLectures +
                ", lectureLength=" + lectureLength +
                ", noOfPracticals=" + noOfPracticals +
                ", practicalLength=" + practicalLength +
                '}';
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


