package csc1035.project2.model;
import org.hibernate.Session;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Booking class will serve as the mapping class for the Booking Table.
 * The booking table will contain every reservation that has been booked
 * by students and staff for specific rooms in the establishment.
 *
 * @author Lesther, Jr Llacuna
 * JavaDoc and comments by Ben Mitchell

 *  Booking_getAll This query will return all of the bookings from the booking table
 *  Booking_thisWeek This query will return all of the bookings that are in the current week
 */

@Entity
@Table(name = "Booking")
@NamedQueries({
        @NamedQuery(name = "Booking_getAll", query = "FROM Booking"),
        @NamedQuery(name = "Booking_thisWeek", query = "FROM Booking b where yearweek(b.endDateTime) = yearweek(now())")
})

public class Booking {
    // Mapping varaibles to columns and setting the primary key field
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "start")
    private LocalDateTime startDateTime;

    @Column(name = "end")
    private LocalDateTime endDateTime;

    //Creating the relationships to the other Tables
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Booking() {}

    /**
     *
     * @param startDateTime This will be the starting time the date is booked for.
     * @param endDateTime This will be the time the booking ends
     * @param room This is the room that will bbe booked for the duration
     */
    public Booking(LocalDateTime startDateTime, LocalDateTime endDateTime, Room room) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.room = room;
    }

    /**
     *
     * @param startDateTime startDateTime This will be the starting time the date is booked for.
     * @param endDateTime This will be the time the booking ends
     * @param room room This is the room that will bbe booked for the duration
     * @param student This will be the student that booked the room
     */
    public Booking(LocalDateTime startDateTime, LocalDateTime endDateTime, Room room, Student student) {
        this(startDateTime, endDateTime, room);
        this.student = student;
    }

    /**
     *
     * @param startDateTime This will be the starting time the date is booked for.
     * @param endDateTime This will be the time the booking ends
     * @param room This is the room that will bbe booked for the duration
     * @param staff This will be the Staff member that booked the room
     */
    public Booking(LocalDateTime startDateTime, LocalDateTime endDateTime, Room room, Staff staff) {
        this(startDateTime, endDateTime, room);
        this.staff = staff;
    }

    /**
     * @return returns all of the bookings
     */
    public static List<Booking> getAll(Session session){
        return session
                .createNamedQuery("Booking_getAll", Booking.class)
                .getResultList();
    }

    /**
     * @return this will return all of the bookings for the current week
     */
    public static List<Booking> getBookingsThisWeek(Session session){
        return session
                .createNamedQuery("Booking_thisWeek", Booking.class)
                .getResultList();
    }

    /**
     * @return Returns start date time for booking object
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     *  startDateTime A setter for the startDateTime attibute
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return Returns endDateTime for booking object
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     *  setEndDateTime A setter for the endDateTime attibute
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * @return Returns Student that booked the room
     */
    public Student getStudent() {
        return student;
    }

    /**
     * setStudent A setter for the Student that booked the room
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return Returns staff member that booked the room
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * setStaff A setter for the Staff that booked the room
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     * @return Module that the room is booked for
     */
    public Module getModule() {
        return module;
    }

    /**
     * setModule A setter for the Module that the room is booked for
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     * @return returns the room that is booked
     */
    public Room getRoom() {
        return room;
    }

    /**
     *  room sets the room thats booked
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * @return Creates human readable print for the booking class
     */
    @Override
    public String toString() {
        return "Booking{" +
                "startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", student=" + student +
                ", room=" + room +
                '}';
    }
}
