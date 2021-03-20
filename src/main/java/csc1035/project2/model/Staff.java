package csc1035.project2.model;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Staff")
@NamedQueries({
        @NamedQuery(name = "Staff_getAllBookings", query = "FROM Booking b WHERE b.staff = :staff"),
        @NamedQuery(name = "Staff_getActiveBookings", query = "FROM Booking b WHERE b.staff = :staff AND b.endDateTime >= now()"),
        @NamedQuery(name = "Staff_getBookingsThisWeek", query = "FROM Booking b WHERE b.staff = :staff and yearweek(b.endDateTime) = yearweek(now())")
})
public class Staff{

    @Id
    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "staff")
    private List<Booking> booking;

    @ManyToMany
    @JoinTable(
            name = "StaffModule",
            joinColumns = {@JoinColumn(name = "staff_id")},
            inverseJoinColumns = {@JoinColumn(name = "module_id")})
    private Set<Module> modules = new HashSet<>();

    public Staff() {}

    public Staff(String staffId, String firstName, String lastName) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public List<Booking> getAllBookings(Session session) {
        return session
                .createNamedQuery("Staff_getAllBookings", Booking.class)
                .setParameter("staff", this)
                .getResultList();
    }

    public List<Booking> getActiveBookings(Session session) {
        return session
                .createNamedQuery("Staff_getActiveBookings", Booking.class)
                .setParameter("staff", this)
                .getResultList();
    }

    public List<Booking> getBookingsThisWeek(Session session) {
        return session
                .createNamedQuery("Staff_getBookingsThisWeek", Booking.class)
                .setParameter("staff", this)
                .getResultList();
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
