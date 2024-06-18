import java.util.ArrayList;
import java.util.Scanner;

public class UserEntryArrayList {
    public static void main(String[] args) {
        // Create an ArrayList of integers
        ArrayList<Integer> numbers = new ArrayList<>();
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Get the number of elements to add
        System.out.print("Enter the number of elements to add: ");
        int numberOfElements = scanner.nextInt();

        // Add elements to the ArrayList based on user input
        for (int i = 0; i < numberOfElements; i++) {
            System.out.print("Enter element #" + (i + 1) + ": ");
            int element = scanner.nextInt();
            numbers.add(element);
        }

        // Display the initial ArrayList
        System.out.println("Initial ArrayList: " + numbers);

        // Access and manipulate elements
        if (!numbers.isEmpty()) {
            int firstElement = numbers.get(0);
            System.out.println("First element: " + firstElement);
            System.out.println("Updated ArrayList: " + numbers);
        } else {
            System.out.println("ArrayList is empty.");
        }

        // Remove an element by value
        System.out.print("Enter the element to remove: ");
        int elementToRemove = scanner.nextInt();
        if (numbers.contains(elementToRemove)) {
            numbers.remove(Integer.valueOf(elementToRemove));
            System.out.println("Element removed. Updated ArrayList: " + numbers);
        } else {
            System.out.println("Element not found in the ArrayList.");
        }

        // Display the final ArrayList
        System.out.println("Final ArrayList: " + numbers);

        // Close the scanner
        scanner.close();
    }
}
