import csc1035.project2.io.UserInterface;
import csc1035.project2.model.*;
import csc1035.project2.controller.*;
import csc1035.project2.model.Module;
import org.hibernate.SessionFactory;
import csc1035.project2.util.HibernateUtil;

import java.util.Scanner;

/**
 * Main class to be ran by the user.
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        IController ic = new Controller(sf);
        Scanner sc = new Scanner(System.in);

        UserInterface ui = new UserInterface(sc, ic, sf);
        ui.main();
    }
}
