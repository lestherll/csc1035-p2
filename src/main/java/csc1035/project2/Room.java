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
}