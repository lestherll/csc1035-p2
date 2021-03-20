package util;

import csc1035.project2.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HibernateUtilTest {

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
}