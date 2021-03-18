package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Staff")
@NamedQueries({
        @NamedQuery(name = "Staff_getAllBookings", query = "FROM Booking b WHERE b.staff = :staff"),
        @NamedQuery(name = "Staff_getActiveBookings", query = "FROM Booking b WHERE b.staff = :staff AND b.endDateTime >= now()"),
})
public class Staff implements Member{

    @Id
    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "staff")
    private List<Booking> booking;

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

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
