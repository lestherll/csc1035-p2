package csc1035.project2.io;

import csc1035.project2.model.Student;
import csc1035.project2.controller.IController;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;
    private final IController iController;

    public UserInterface(Scanner scanner, IController iController) {
        this.scanner = scanner;
        this.iController = iController;
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

}
