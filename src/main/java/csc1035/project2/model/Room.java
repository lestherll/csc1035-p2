package csc1035.project2.model;

import org.hibernate.Session;
import javax.persistence.*;
import java.util.List;

/**
 * The room class will be used to identify which room has been booked and which ones are open.
 * This class contains room id to identify each room individually, maximum capacity to tell you how many people can be in that room
 * and how many can be in that room under social distancing circumstances
 * @author
 * JavaDoc and comments by Ben Micthell
 *
 *
 * Named Queries:
 *    "Room_getAll"  - Will return all of the rooms in the room Table
 *    "Room_getActiveBookings" - Will return all of the rooms currently booked
 */
@Entity
@Table(name = "Room")
@NamedQueries({
        @NamedQuery(name = "Room_getAll", query = "FROM Room"),
        @NamedQuery(name = "Room_getActiveBookings", query = "FROM Booking b WHERE b.room = :room")
})
public class Room {
    //Create variables, assign them to the columns in the Room Table and set Primary key field
    @Id
    @Column(name = "room_id")
    private String roomId;

    @Column(name = "type")
    private String type;

    @Column(name = "max_cap")
    private int maxCapacity;

    @Column(name = "sd_max_cap")
    private int sdMaxCapacity;

    // Create the relationship to the booking table in the database
    @OneToMany(mappedBy = "room")
    private List<Booking> booking;

    public Room() {
    }

    /**
     *
     * @param roomId This will be the primary key and the unique identifier for all of the rooms
     * @param maxCapacity This will signify the maximum amount of people that can occupy a room at once
     * @param sdMaxCapacity This will signify the maximum amount of people that can occupy a room at once under social distancing conditions.
     * @param type This is the type of room.
     */
    public Room(String roomId, int maxCapacity, int sdMaxCapacity, String type) {
        this.roomId = roomId;
        this.maxCapacity = maxCapacity;
        this.sdMaxCapacity = sdMaxCapacity;
        this.type = type;
    }

    //Create all of the get and setter methods so yuo are able to set and return individual attributes

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

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", type='" + type + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", sdMaxCapacity=" + sdMaxCapacity +
                '}';
    }

    // Query Methods

    /**
     *
     * @param session Opens the session to the database
     * @return This will return all of the rooms that are in the room table
     */
    public static List<Room> getAll(Session session) {
        return session
                .createNamedQuery("Room_getAll", Room.class)
                .getResultList();
    }

    /**
     *
     * @param session Opens the session to the database
     * @return Returns all of the rooms that are actively booked / occupied
     */
    public List<Booking> getActiveBookings(Session session) {
        return session
                .createNamedQuery("Room_getActiveBookings", Booking.class)
                .setParameter("room", this)
                .getResultList();
    }
}
