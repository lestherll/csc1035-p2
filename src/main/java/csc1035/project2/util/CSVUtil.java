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

public class CSVUtil {

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

    public static <E> void printList(Class<E> c, List<E> data) {
        for (E e: data) {
            System.out.println(e);
        }
    }

    public static void printList(List<List<String>> data) {
        for (List<String> list: data) {
            System.out.println(list);
        }
    }

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

}
