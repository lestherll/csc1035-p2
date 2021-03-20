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
 */

@Entity
@Table(name = "Booking")
@NamedQueries({
        @NamedQuery(name = "Booking_getAll", query = "FROM Booking"),
        @NamedQuery(name = "Booking_thisWeek", query = "FROM Booking b where yearweek(b.endDateTime) = yearweek(now())")
})
public class Booking {

    @Id
    @Column(name = "booking_id")
    private String id;

    @Column(name = "start")
    private LocalDateTime startDateTime;

    @Column(name = "end")
    private LocalDateTime endDateTime;

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

    public Booking(LocalDateTime startDateTime, LocalDateTime endDateTime, Room room) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.room = room;
    }

    public Booking(LocalDateTime startDateTime, LocalDateTime endDateTime, Room room, Student student) {
        this(startDateTime, endDateTime, room);
        this.student = student;
    }

    public Booking(LocalDateTime startDateTime, LocalDateTime endDateTime, Room room, Staff staff) {
        this(startDateTime, endDateTime, room);
        this.staff = staff;
    }

    public static List<Booking> getAll(Session session){
        return session
                .createNamedQuery("Booking_getAll", Booking.class)
                .getResultList();
    }

    public static List<Booking> getBookingsThisWeek(Session session){
        return session
                .createNamedQuery("Booking_thisWeek", Booking.class)
                .getResultList();
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

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
