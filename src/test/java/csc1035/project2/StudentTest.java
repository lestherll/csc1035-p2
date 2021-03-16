package csc1035.project2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private static Student s1 = new Student("testId", "testFirstName", "testLastName");

    @Test
    void getStudentId() {
        assertEquals("testId", s1.getStudentId(), "studentId doesn't match");
    }

    @Test
    void getFirstName() {
    }

    @Test
    void getLastName() {
    }
}