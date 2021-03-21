package csc1035.project2.model;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * This is the Student class and its purpose is to show what students will be taking a module and show which student booked a room.
 * It will contain a unique student_id which will serve as the primary key for the database and first and last name.
 *
 * @author
 * JavaDoc and Comments by Ben Mitchell
 *
 *
 * Named Queries:
 *      "Student_getAllBookings" - Returns all of the bookings made by a student
 *      "Student_getActiveBookings" - Returns all of the active student bookings
 *      "Student_getBookingsThisWeek" - Returns all of the bookings made by the student in this week
 */
@Entity
@Table(name = "Student")
@NamedQueries({
        @NamedQuery(
                name = "Student_getAllBookings",
                query = "FROM Booking b where b.student = :student"
        ),
        @NamedQuery(
                name = "Student_getActiveBookings",
                query = "FROM Booking b where b.student = :student and b.endDateTime >= now()"
        ),
        @NamedQuery(
                name = "Student_getBookingsThisWeek",
                query = "FROM Booking b where b.student = :student and yearweek(b.endDateTime) = yearweek(now())"
        ),

})
public class Student {
    //Create variables, assign them to the columns to the Student Table and set Primary key field
    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    // Create all the relationships to the other tables in the database
    @OneToMany(mappedBy = "student")
    private List<Booking> booking;
    // Create the join table for the StudentModule and set the relationship to many to many
    @ManyToMany
    @JoinTable(
            name = "StudentModule",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "module_id")})
    private Set<Module> modules = new HashSet<>();

    public Student() {}

    /**
     *
     * @param studentId This is used as a unique identifier and the primary key field
     * @param firstName This is used to store the first name of the student
     * @param lastName This is used to store the last name of the student
     */

    public Student(String studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getStudentId() {
        return this.studentId;
    }

    //Create all of the get and setter methods so you are able to set and return individual attributes
    public void setStudentId(String newStudentId) {
        this.studentId = newStudentId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    /**
     * @param session Creates session to the database
     * @return This will return all of the bookings made by a student
     */
    public List<Booking> getAllBookings(Session session) {
        return session
                .createNamedQuery("Student_getAllBookings", Booking.class)
                .setParameter("student", this)
                .getResultList();
    }

    /**
     *
     * @param session Creates session to the database
     * @return This will return all of the active bookings made by the student.
     */
    public List<Booking> getActiveBookings(Session session) {
        return session
                .createNamedQuery("Student_getActiveBookings", Booking.class)
                .setParameter("student", this)
                .getResultList();
    }

    /**
     *
     * @param session Creates session to the database
     * @return Th is will return all of the bookings this week made by a a student
     */
    public List<Booking> getBookingsThisWeek(Session session) {
        return session
                .createNamedQuery("Student_getBookingsThisWeek", Booking.class)
                .setParameter("student", this)
                .getResultList();
    }

    /**
     *
     * @return Returns a human readable print of the Student class / object
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}