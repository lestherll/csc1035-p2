package csc1035.project2.io;

import csc1035.project2.controller.Controller;
import csc1035.project2.model.Booking;
import csc1035.project2.model.Room;
import csc1035.project2.model.Staff;
import csc1035.project2.model.Student;
import csc1035.project2.model.Module;
import csc1035.project2.controller.IController;
import csc1035.project2.util.HibernateUtil;
import csc1035.project2.util.SlotHandlerUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 * UserInterface contains the main program loop for the command line program.
 * This also houses everything that the user will use to interact with the
 * database tables.
 */
public class UserInterface {

    private final Scanner scanner;
    private final IController iController;
    private final SessionFactory sessionFactory;


    /**
     * If no argument is passed, the object will create the
     * dependencies by default
     */
    public UserInterface() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
        this.scanner = new Scanner(System.in);
        this.iController = new Controller(sessionFactory);
    }

    /**
     * Arguments can be passed so that there is better support
     * for objects higher in the dependency hierarchy
     * @param scanner will be used for taking inputs
     * @param iController is the CRUD object that will
     *                    create, read, update, delete
     *                    records
     * @param sessionFactory the factory pattern that will
     *                       be used for creating sessions
     */
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


    /**
     * @return student object created from the inputs
     */
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

    /**
     * The user will book based on slots. Slots are equal distribution
     * of time intervals between the opening and closing times defined
     * in SlotHandlerUtil class.
     * @return LocalTime[] object with length 2 to be used for start
     * and end time of a booking
     */
    // Case 1 Methods
    public LocalTime[] getTimeBySlot() {
        int slot = enterNum(1, 20, "Enter time slot [1-10](1hr/slot from 8am)");
        return SlotHandlerUtil.slotToTimeRange(slot);
    }

    /**
     * @return a LocalDate object created from the inputs of the user
     */
    public LocalDate getBookingDate() {
        Calendar calendar= Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = enterNum(calendar.get(Calendar.MONTH)+1, 12, "Enter month");
        int day = enterNum(calendar.get(Calendar.DATE), calendar.getActualMaximum(Calendar.DATE), "Enter day");
        return LocalDate.of(year, month, day);
    }

    /**
     * @return a Room object that is fetched using its ID
     */
    public Room getRoomById() {
        String roomId = enterStr("Enter room id");
        return (Room) iController.getById(Room.class, roomId);
    }

    /**
     * @return a Staff object that is fetched using its ID
     */
    public Staff getStaffById() {
        String staffId = enterStr("Enter staff id");
        return (Staff) iController.getById(Staff.class, staffId);
    }

    /**
     * @return a Student object that is fetched using its ID
     */
    public Student getStudentById() {
        String studId = enterStr("Enter student id");
        return (Student) iController.getById(Student.class, studId);
    }

    /**
     * @return a Module object that is fetched using its ID
     */
    public Module getModuleById() {
        String moduleId = enterStr("Enter module id");
        return (Module) iController.getById(Module.class, moduleId);
    }


    /**
     * The method merges a LocalDate and LocalTime object
     * @param date
     * @param time
     * @return a LocalDateTime merged object from the arguments.
     */
    public LocalDateTime mergeDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }


    /**
     * Choice decides whether a booking was made by staff or a student
     * @param choice passed from the main menu game loop
     * @return a Booking object
     */
    public Booking createBooking(int choice) {
        LocalTime[] lt = getTimeBySlot();
        LocalDate ld = getBookingDate();
        Room room = getRoomById();
        Module module = getModuleById();
        LocalDateTime start = mergeDateTime(ld, lt[0]);
        LocalDateTime end = mergeDateTime(ld, lt[1]);

        Booking booking = new Booking();

        if (choice == 1) {
            Staff staff = getStaffById();
            booking = new Booking(start, end, module, room, staff);
        } else if (choice == 2) {
            Student student = getStudentById();
            booking = new Booking(start, end, module, room, student );
        }
        return booking;
    }

    /**
     * The main program branching-based loop that is
     * directly based on the clients requirements.
     * The user will select at the start what system
     * they want to interact with. RBS deals with bookings
     * and rooms. TBS deals with timetables for the school,
     * staff, and students.
     *
     * The program will only exit if the user chose to.
     */
    public void main() {
        // Declare model variables
        Session session;
        Staff staff;
        Student student;
        Room room;
        Booking booking;

        int choice;
        int choice1;
        int choice2;
        System.out.println("Welcome!!!");
        while (true) {
            System.out.println("Room Booking and Timetable Manager. Would you like to: \n"
                    + "1. Use the Room Booking System \n"
                    + "2. Use the Timetabling System \n"
                    + "3. Exit \n");
            choice = enterNum(1, 3, "Please enter your choice");
            switch (choice) {
                case 1 -> {
                    System.out.println("You selected to use the Room Booking System. Would you like to: \n"
                            + "1. Book a room \n"
                            + "2. Cancel a room booking \n"
                            + "3. Get a list of available rooms \n"
                            + "4. Get the timetable of a room \n"
                            + "5. Update room details \n");
                    choice1 = enterNum(1,5,"Please enter your choice");
                    switch (choice1) {
                        case 1 -> {
                            System.out.println("Room Booking:\n" +
                                    "1. Staff\n" +
                                    "2. Student\n" +
                                    "3. Quit room booking\n");
                            choice2 = enterNum(1,3, "Enter choice");
                            booking = createBooking(choice2);
                            iController.save(booking);

                            Booking test = (Booking) iController.getById(Booking.class, booking.getId());
                            try {
                                if (test.getId() == booking.getId()) {
                                    System.out.println("Successfully booked");
                                }
                            } catch (Exception e) {
                                System.out.println("Failed to book");
                            }

                        }
                        case 2 -> {
                            System.out.println("You selected to cancel a room booking");
                            int bookingId = enterNum(1, 999999,"Enter booking id");
                            iController.delete(Booking.class, bookingId);
                        }
                        case 3 -> {
                            System.out.println("You selected to get a list of available rooms");
                            List<Room> rooms = iController.getAll(Room.class);
                            for (Room r: rooms) {
                                System.out.println("\nid: " + r.getRoomId()
                                        + "\ntype: " + r.getType()
                                        + "\nmax cap: " + r.getMaxCap()
                                        + "\nSD max cap: " +r.getSdMaxCap() + "\n");
                            }
                        }
                        case 4 -> {
                            System.out.println("You selected to get the timetable of a room");
                            room = getRoomById();
                            session = sessionFactory.openSession();
                            List<Booking> bookings = room.getActiveBookings(session);

                            Object booker;
                            if (bookings.size() != 0) {
                                for(Booking b: bookings) {

                                    if (b.getStudent() == null) {
                                        booker = b.getStaff();
                                    } else {
                                        booker = b.getStudent();
                                    }

                                    System.out.println("\nRoom: " + b.getRoom() +
                                            "\nStart: " + b.getStartDateTime() +
                                            "\nEnd: " + b.getEndDateTime() +
                                            "\nBooker: " + booker + "\n"
                                    );
                                }
                            } else {
                                System.out.println("There are no active bookings for this room");
                            }

                        }
                        case 5 -> {
                            System.out.println("You selected to update room details");
                            room = getRoomById();
                            room.setType(enterStr("Enter new type"));
                            room.setMaxCap(enterNum(1, 999, "Enter max cap"));
                            room.setSdMaxCap(enterNum(1, 999, "Enter Social Dis Cap"));
                            iController.update(room);
                        }
                    }
                }
                case 2 -> {
                    System.out.println("You selected to use the Timetabling System. Would you like to: \n"
                            + "1. Get a student timetable \n"
                            + "2. Get a staff timetable \n"
                            + "3. Create a school timetable \n");
                    choice2 = enterNum(1,3,"Please enter your choice");
                    switch (choice2) {
                        case 1 -> {
                            System.out.println("You selected to produce a timetable for a student");
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
