package csc1035.project2.util;

import csc1035.project2.controller.Controller;
import csc1035.project2.controller.IController;
import csc1035.project2.model.*;
import csc1035.project2.model.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * CSVUtil is the helper class for reading csv files
 * from resources
 */
public class CSVUtil {

    /**
     * the main use of CSVUtil is to call this method
     * which reads a csv file from a given path
     * @param path the path of the file
     * @return a nested List of strings split by comma ","
     */
    public static List<List<String>> read(String path) {
        List<List<String>> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                result.add(Arrays.asList(scanner.nextLine().split(",")));
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return result;
    }

    /**
     * Calls bulkListUpdate on all csv files read using the
     * different read methods
     * @param ic the CRUD controller that will save the entries
     */
    public static void saveOrUpdateFromCSV(IController ic) {
        ic.bulkListUpdate(readRoom());
        ic.bulkListUpdate(readModule());
        ic.bulkListUpdate(readStaff());
        ic.bulkListUpdate(readStudent());
        ic.bulkListUpdate(readStaffModule());
        ic.bulkListUpdate(readStudentModule());
    }

    /**
     * Calls bulkListSave on all csv files read using the different
     * read methods
     * @param ic the CRUD controller that will save the entries
     */
    public static void saveFromCSV(IController ic) {
        ic.bulkListSave(readRoom());
        ic.bulkListSave(readModule());
        ic.bulkListSave(readStaff());
        ic.bulkListSave(readStudent());
        ic.bulkListSave(readStaffModule());
        ic.bulkListSave(readStudentModule());
    }

    /**
     * Helper method for printing a list of some object/class
     * @param c the class of the objects in the list
     * @param data the list that contains the data of c class
     * @param <E>
     */
    public static <E> void printList(Class<E> c, List<E> data) {
        for (E e: data) {
            System.out.println(e);
        }
    }

    /**
     * Helper method for pringting a nested list of strings
     * @param data the nested list of strings
     */
    public static void printList(List<List<String>> data) {
        for (List<String> list: data) {
            System.out.println(list);
        }
    }

    /**
     * Reads rooms.csv file and creates a list
     * of Room objects out of it
     * @return list of Room
     */
    public static List<Room> readRoom() {
        String path = "src/main/resources/rooms.csv";
        List<List<String>> res = read(path);
        res = read(path).subList(1, res.size());

        List<Room> list = new ArrayList<>();

        for (List<String> currList : res) {
            list.add(new Room(
                    currList.get(0),
                    Integer.parseInt(currList.get(2)),
                    Integer.parseInt(currList.get(3)),
                    currList.get(1)
            ));
        }
        return list;
    }

    /**
     * Reads staff.csv file and creates a list
     * of Staff objects out of it
     * @return list of Staff
     */
    public static List<Staff> readStaff() {
        String path = "src/main/resources/staff.csv";
        List<List<String>> res = read(path);
        res = read(path).subList(1, res.size());

        List<Staff> list = new ArrayList<>();

        for (List<String> currList : res) {
            list.add(new Staff(currList.get(0), currList.get(1), currList.get(2)));
        }
        return list;
    }

    /**
     * Reads students.csv file and creates a list
     * of Student objects
     * @return list of Students
     */
    public static List<Student> readStudent() {
        String path = "src/main/resources/students.csv";
        List<List<String>> res = read(path);
        res = read(path).subList(1, res.size());

        List<Student> list = new ArrayList<>();

        for (List<String> currList : res) {
            list.add(new Student(currList.get(0), currList.get(1), currList.get(2)));
        }
        return list;
    }

    /**
     * Reads modules.csv file and creates a list
     * of Module objects
     * @return list of Modules
     */
    public static List<Module> readModule() {
        String path = "src/main/resources/modules.csv";
        List<List<String>> res = read(path);
        res = read(path).subList(1, res.size());

        List<Module> list = new ArrayList<>();

        for (List<String> currList : res) {
            list.add(new Module(
                    currList.get(0),
                    currList.get(1),
                    Integer.parseInt(currList.get(2)),
                    Integer.parseInt(currList.get(3)),
                    Integer.parseInt(currList.get(4)),
                    Integer.parseInt(currList.get(5)),
                    Integer.parseInt(currList.get(6)),
                    Integer.parseInt(currList.get(7))
                    )
            );
        }
        return list;
    }

    /**
     * Reads staff_module.csv and creates a list
     * of StaffModule objects
     * @return list of StaffModules
     */
    public static List<StaffModule> readStaffModule() {
        String path = "src/main/resources/staff_module.csv";
        List<List<String>> res = read(path);
        res = read(path).subList(1, res.size());

        List<StaffModule> list = new ArrayList<>();

        for (List<String> currList : res) {
            StaffModule currSM = new StaffModule(currList.get(1), currList.get(0));
            list.add(currSM);
        }

        return list;
    }

    /**
     * Reads student_module.csv and creates a list
     * of StudentModule objects
     * @return list of StudentModules
     */
    public static List<StudentModule> readStudentModule() {
        String path = "src/main/resources/student_module.csv";
        List<List<String>> res = read(path);
        res = read(path).subList(1, res.size());

        List<StudentModule> list = new ArrayList<>();
        for (List<String> currList : res) {
            StudentModule currSM = new StudentModule(currList.get(1), currList.get(0));
            list.add(currSM);
        }
        return list;
    }

}
