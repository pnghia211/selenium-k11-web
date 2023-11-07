package api_learning;

import java.util.ArrayList;
import java.util.List;

public class GenericExample {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.add(3);
        printList(intList);

        List<Object> objectList = new ArrayList<>();
        objectList.add(3);
        printList(objectList);
    }

    private static void printList (List<?> myList) {
        System.out.println(myList);
    }
}
