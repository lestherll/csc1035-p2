package csc1035.project2;

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
    @Column(name = "start")
    private LocalDateTime startDateTime;

    @Column(name = "end")
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

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

    // Constructor when a staff books

    public static List<?> getAllBookings(Session session){
        Query query = session.createNamedQuery("Booking_getAll", Booking.class);
        return query.getResultList();
    }

    public static List<?> getBookingsThisWeek(Session session){
        Query query = session.createNamedQuery("Booking_thisWeek", Booking.class);
        return query.getResultList();
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
