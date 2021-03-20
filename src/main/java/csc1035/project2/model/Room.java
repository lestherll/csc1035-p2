package csc1035.project2.model;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Room")
@NamedQueries({
        @NamedQuery(name = "Room_getAll", query = "FROM Room"),
        @NamedQuery(name = "Room_getActiveBookings", query = "FROM Booking b WHERE b.room = :room")
})
public class Room {

    @Id
    @Column(name = "room_id")
    private String roomId;

    @Column(name = "max_cap")
    private int maxCapacity;

    @Column(name = "sd_max_cap")
    private int sdMaxCapacity;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "room")
    private List<Booking> booking;

    public Room() {
    }

    public Room(String roomId, int maxCapacity, int sdMaxCapacity, String type) {
        this.roomId = roomId;
        this.maxCapacity = maxCapacity;
        this.sdMaxCapacity = sdMaxCapacity;
        this.type = type;
    }

    // Setters and Getters

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String newRoomId) {
        this.roomId = newRoomId;
    }

    public int getMaxCap() {
        return this.maxCapacity;
    }

    public void setMaxCap(int newMaxCap) {
        this.maxCapacity = newMaxCap;
    }

    public int getSdMaxCap() {
        return this.sdMaxCapacity;
    }

    public void setSdMaxCap(int newSdMaxCap) {
        this.sdMaxCapacity = newSdMaxCap;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    // Query Methods
    public static List<Room> getAll(Session session) {
        return session
                .createNamedQuery("Room_getAll", Room.class)
                .getResultList();
    }

    public List<Booking> getActiveBookings(Session session) {
        return session
                .createNamedQuery("Room_getActiveBookings", Booking.class)
                .setParameter("room", this)
                .getResultList();
    }
}
