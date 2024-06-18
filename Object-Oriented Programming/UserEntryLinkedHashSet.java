import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UserEntryLinkedHashSet {
    public static void main(String[] args) {
        // Create a LinkedHashSet of integers
        Set<Integer> numbers = new LinkedHashSet<>();
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Get the number of elements to add
        System.out.print("Enter the number of elements to add: ");
        int numberOfElements = scanner.nextInt();

        // Add elements to the LinkedHashSet based on user input
        for (int i = 0; i < numberOfElements; i++) {
            System.out.print("Enter element #" + (i + 1) + ": ");
            int element = scanner.nextInt();
            numbers.add(element);
        }

        // Display the initial LinkedHashSet
        System.out.println("Initial LinkedHashSet: " + numbers);

        // Access and manipulate elements (Note: LinkedHashSet maintains insertion order)
        System.out.println("LinkedHashSet maintains insertion order.");

        // Remove an element based on user input
        System.out.print("Enter the element to remove: ");
        int elementToRemove = scanner.nextInt();
        if (numbers.contains(elementToRemove)) {
            numbers.remove(elementToRemove);
            System.out.println("Element removed. Updated LinkedHashSet: " + numbers);
        } else {
            System.out.println("Element not found in the LinkedHashSet.");
        }

        // Display the final LinkedHashSet
        System.out.println("Final LinkedHashSet: " + numbers);

        // Close the scanner
        scanner.close();
    }
}
