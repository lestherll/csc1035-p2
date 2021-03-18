package csc1035.project2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Room room = new Room("0.1234", 100, 50, "Test Room");

    @Test
    void getRoomId() {
        assertEquals("0.1234", room.getRoomId());
    }

    @Test
    void getMaxCap() {
        assertEquals(100, room.getMaxCap());
    }

    @Test
    void getSdMaxCap() {
        assertEquals(50, room.getSdMaxCap());
    }

    @Test
    void getType() {
        assertEquals("Test Room", room.getType());
    }
}