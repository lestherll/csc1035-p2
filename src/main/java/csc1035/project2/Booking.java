package csc1035.project2;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Booking class will serve as the mapping class for the Booking Table.
 * The booking table will contain every reservation that has been booked
 * by students and staff for specific rooms in the establishment.
 *
 * @author Lesther, Jr Llacuna
 */

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int bookingId;

    @Column(name = "start")
    private LocalDateTime startDateTime;

    @Column(name = "end")
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Booking() {}

    public Booking(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Booking(int bookingId, LocalDateTime startDateTime, LocalDateTime endDateTime, Student student) {
        this(startDateTime, endDateTime);
        this.student = student;
    }

    // Constructor when a staff books

    // getters and setters
}
