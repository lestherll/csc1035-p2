package csc1035.project2;

import javax.persistence.*;

@Entity
@Table(name = "Room")
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

    public Room() {
    }

    public Room(String roomId, int maxCapacity, int sdMaxCapacity, String type) {
        this.roomId = roomId;
        this.maxCapacity = maxCapacity;
        this.sdMaxCapacity = sdMaxCapacity;
        this.type = type;
    }
}
