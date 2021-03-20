package csc1035.project2;

import csc1035.project2.model.Student;
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
        assertEquals("testFirstName", s1.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("testLastName", s1.getLastName());
    }
}