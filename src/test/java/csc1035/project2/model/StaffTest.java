package csc1035.project2.model;

import csc1035.project2.model.Staff;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {

    Staff staff = new Staff("NUC999999", "John", "Doe");

    @Test
    void getStaffId() {
        assertEquals("NUC999999", staff.getStaffId());
    }

    @Test
    void getFirstName() {
        assertEquals("John", staff.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Doe", staff.getLastName());
    }
}