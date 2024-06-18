import java.util.LinkedList;
import java.util.Scanner;

public class UserEntryLinkedList {
    public static void main(String[] args) {
        // Create a LinkedList of integers
        LinkedList<Integer> numbers = new LinkedList<>();
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Get the number of elements to add
        System.out.print("Enter the number of elements to add: ");
        int numberOfElements = scanner.nextInt();

        // Add elements to the LinkedList based on user input
        for (int i = 0; i < numberOfElements; i++) {
            System.out.print("Enter element #" + (i + 1) + ": ");
            int element = scanner.nextInt();
            numbers.add(element);
        }

        // Display the initial LinkedList
        System.out.println("Initial LinkedList: " + numbers);

        // Access and manipulate elements
        if (!numbers.isEmpty()) {
            int firstElement = numbers.getFirst();
            System.out.println("First element: " + firstElement);
            System.out.println("Updated LinkedList: " + numbers);
        } else {
            System.out.println("LinkedList is empty.");
        }

        // Remove an element by value
        System.out.print("Enter the element to remove: ");
        int elementToRemove = scanner.nextInt();
        if (numbers.contains(elementToRemove)) {
            numbers.remove(Integer.valueOf(elementToRemove));
            System.out.println("Element removed. Updated LinkedList: " + numbers);
        } else {
            System.out.println("Element not found in the LinkedList.");
        }

        // Display the final LinkedList
        System.out.println("Final LinkedList: " + numbers);

        // Close the scanner
        scanner.close();
    }
}
