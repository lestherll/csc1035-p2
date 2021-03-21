package csc1035.project2.util;

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
}
