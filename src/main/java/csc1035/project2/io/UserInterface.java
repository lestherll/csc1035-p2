package csc1035.project2.io;

import csc1035.project2.controller.Controller;
import csc1035.project2.model.Student;
import csc1035.project2.controller.IController;
import csc1035.project2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;
    private final IController iController;
    private final SessionFactory sessionFactory;

    public UserInterface() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
        this.scanner = new Scanner(System.in);
        this.iController = new Controller(sessionFactory);
    }

    public UserInterface(Scanner scanner, IController iController, SessionFactory sessionFactory) {
        this.scanner = scanner;
        this.iController = iController;
        this.sessionFactory = sessionFactory;
    }

    /**
     * This allows the user to enter integer inputs based on the minimum
     * and maximum arguments that have been passed by the user. Does not
     * allow empty/blank values.
     * @param min this depicts the minimum integer input
     * @param max this depicts the maximum integer input
     * @param message the message shown when the program asks for input
     * @return the valid input by the user
     */
    public int enterNum(int min, int max, String message) {
        int number;
        String input;

        while (true){
            System.out.printf("\n%s: ", message);
            input = this.scanner.nextLine();
            if (!input.isBlank()) {
                try {
                    number = Integer.parseInt(input);
                    if ((number <= max) && (number >= min)) {
                        break;
                    }
                    System.out.printf("%s Outside of range\n", number);
                }
                catch (NumberFormatException ignore) {
                    System.out.println("Invalid input");
                }
            }
            else {
                System.out.println("Can't enter empty val");
            }

        }
        return number;
    }

    /**
     * A basic double input for the user. Does not allow empty/blank values.
     * @param message argument that the user must pass to be
     *                displayed when the program asks for input
     * @return validated input that the user will enter
     */
    public double enterDouble(String message) {
        double number;
        String input;

        while (true) {
            System.out.printf("\n%s: ", message);
            input = this.scanner.nextLine();
            if (!input.isBlank()) {
                try {
                    number = Double.parseDouble(input);
                    break;
                } catch (NumberFormatException ignore) {
                    System.out.println("Invalid input");
                }
            }
            else {
                System.out.println("Can't enter empty val");
            }

        }
        return number;
    }

    /**
     * Allows the user to input string. Does not allow empty/blank values.
     * @param message the message shown when the program asks for input
     * @return the input that the user entered in string form
     */
    public String enterStr(String message) {
        String input;
        while (true) {
            System.out.printf("\n%s: ", message);
            input = this.scanner.nextLine();
            if (!input.isBlank()) {
                break;
            }
            else {
                System.out.println("Can't enter empty value");
            }
        }
        return input;
    }

    public Student createStudent() {
        String id, firstName, lastName;
        id = enterStr("Enter ID");
        firstName = enterStr("Enter first name");
        lastName = enterStr("Enter last name");
        return new Student(id, firstName, lastName);
    }

    public void addStudent() {
        this.iController.save(createStudent());
    }

    public void addStudent(Student student) {
        this.iController.save(student);
    }

    public void main() {
        Session s;
        int choice;
        int choice1;
        int choice2;
        System.out.println("Welcome to the Room Booking and Timetable Manager. Would you like to: "
                                                                        + "1. Use the Room Booking System"
                                                                        + "2. Use the Timetabling System"
                                                                        + "3. Exit");
        while (true) {
            choice = enterNum(1, 3, "Please enter your choice");
            switch (choice) {
                case 1 -> {
                    System.out.println("You selected to use the Room Booking System. Would you like to: "
                                                                                            + "1. Book a room"
                                                                                            + "2. Cancel a room booking"
                                                                                            + "3. Get a list of available rooms"
                                                                                            + "4. Get the timetable of a room"
                                                                                            + "5. Update room details");
                    choice1 = enterNum(1,5,"Please enter your choice");
                    switch (choice1) {
                        case 1 -> {
                            System.out.println("You selected to book a room");
                        }
                        case 2 -> {
                            System.out.println("You selected to cancel a room booking");
                        }
                        case 3 -> {
                            System.out.println("You selected to get a list of available rooms");
                        }
                        case 4 -> {
                            System.out.println("You selected to get the timetable of a room");
                        }
                        case 5 -> {
                            System.out.println("You selected to update room details");
                        }
                    }
                }
                case 2 -> {
                    System.out.println("You selected to use the Timetabling System. Would you like to: "
                                                                                            + "1. Get a student timetable"
                                                                                            + "2. Get a staff timetable"
                                                                                            + "3. Create a school timetable");
                    choice2 = enterNum(1,3,"Please enter your choice");
                    switch (choice2) {
                        case 1 -> {
                        }
                        case 2 -> {
                            System.out.println("You selected to get a staff timetable");
                        }
                        case 3 -> {
                            System.out.println("You selected to create a school timetable");
                        }
                    }
                }
            }
            if (choice == 3) {
                System.out.println("You selected to exit the system. Goodbye!");
                break;
            }
        }
    }
}
