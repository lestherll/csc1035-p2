package csc1035.project2.model;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * This is the Staff class and will be used when a room is booked by a staff member to show who has booked the room.
 * It contains information like Staff_id which will be the primary key and unique identifier, first and last name.
 * It will also be used to Show which Staff Members teach the modules
 *
 * @author
 * JavaDoc and Comments by Ben Mitchell
 *
 * Named Queries:
 *      "Staff_getAllBookings" - Returns all booking that have been made by a staff member
 *      "Staff_getActiveBookings" - Returns all active bookings from a staff member
 *      "Staff_getBookingsThisWeek" -Returns bookings from a staff member in the current week
 *
 */

@Entity
@Table(name = "Staff")
@NamedQueries({
        @NamedQuery(name = "Staff_getAllBookings", query = "FROM Booking b WHERE b.staff = :staff"),
        @NamedQuery(name = "Staff_getActiveBookings", query = "FROM Booking b WHERE b.staff = :staff AND b.endDateTime >= now()"),
        @NamedQuery(name = "Staff_getBookingsThisWeek", query = "FROM Booking b WHERE b.staff = :staff and yearweek(b.endDateTime) = yearweek(now())")
})
public class Staff{
    //Create variables, assign them to the columns to the Staff Table and set Primary key field
    @Id
    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "staff")
    private List<Booking> booking;

    // Create the join table for the StaffModule and set the relationship to many to many
    @ManyToMany
    @JoinTable(
            name = "StaffModule",
            joinColumns = {@JoinColumn(name = "staff_id")},
            inverseJoinColumns = {@JoinColumn(name = "module_id")})
    private Set<Module> modules = new HashSet<>();

    public Staff() {}

    /**
     *
     * @param staffId This is used as a unique identifier and the primary key field
     * @param firstName This is used to store the first name of the staff member
     * @param lastName This is used to store the last name of the staff member
     */
    public Staff(String staffId, String firstName, String lastName) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Create all of the get and setter methods so yuo are able to set and return individual attributes
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

    /**
     *
     * @param session Creates database Session
     * @return Returns all bookings by a staff member
     */
    public List<Booking> getAllBookings(Session session) {
        return session
                .createNamedQuery("Staff_getAllBookings", Booking.class)
                .setParameter("staff", this)
                .getResultList();
    }

    /**
     *
     * @param session Creates database Session
     * @return Returns all of the active bookings made by a staff member
     */
    public List<Booking> getActiveBookings(Session session) {
        return session
                .createNamedQuery("Staff_getActiveBookings", Booking.class)
                .setParameter("staff", this)
                .getResultList();
    }

    /**
     * @param session Create database session
     * @return Returns all of the bookings made by staff that occur this week
     */
    public List<Booking> getBookingsThisWeek(Session session) {
        return session
                .createNamedQuery("Staff_getBookingsThisWeek", Booking.class)
                .setParameter("staff", this)
                .getResultList();
    }

    /**
     *
     * @return Returns a human readable print of the Staff class
     *
     */
    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
