package csc1035.project2.controller;

import csc1035.project2.model.Student;
import csc1035.project2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    static void getSessionFactory() {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }

    @BeforeEach
    void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterAll
    static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        System.out.println("SessionFactory destroyed!");
    }

    @AfterEach
    void closeSession() {
        if (session != null) {
            System.out.println("Session wasn't closed");
            session.close();
        }
        System.out.println("Session closed");
    }

    @Test
    void save() {
        IController<Student> ic = new Controller<>(sessionFactory);
        Student student = new Student();
        student.setStudentId("testID");
        ic.save(student);

        Student fetched = ic.getById(Student.class, "testID");
        ic.delete(Student.class, "testId");
        assertEquals(fetched.getStudentId(), student.getStudentId());
    }

    @Test
    void update() {
        IController<Student> ic = new Controller<>(sessionFactory);
        Student student = new Student();
        student.setStudentId("testID1");
        ic.save(student);
        student.setFirstName("testFistName");
        ic.update(student);


        Student fetched = ic.getById(Student.class, "testID1");
        ic.delete(Student.class, "testID1");
        assertEquals(fetched.getFirstName(), student.getFirstName());
    }

}