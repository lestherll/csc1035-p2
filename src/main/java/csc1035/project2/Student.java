package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "student")
    private List<Booking> booking;

    @ManyToMany
    @JoinTable(
            name = "StudentModule",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "module_id")})
    private Set<Module> modules = new HashSet<>();

    public Student() {}

    public Student(String studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getStudentId() {
        return this.studentId;
    }

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

    public List<?> getAllBookings(Session session) {
        Query query = session.createNamedQuery("Student_getAllBookings", Booking.class);
        return query.setParameter("student", this).getResultList();
    }

    public List<?> getActiveBookings(Session session) {
        Query query = session.createNamedQuery("Student_getActiveBookings", Booking.class);
        return query.setParameter("student", this).getResultList();
    }

    public List<?> getBookingsThisWeek(Session session) {
        Query query = session.createNamedQuery("Student_getBookingsThisWeek", Booking.class);
        return query.setParameter("student", this).getResultList();
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}