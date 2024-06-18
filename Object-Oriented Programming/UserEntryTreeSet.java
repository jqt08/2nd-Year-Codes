import java.util.Scanner;
import java.util.TreeSet;
import java.util.Set;

public class UserEntryTreeSet {
    public static void main(String[] args) {
        // Create a TreeSet of integers
        Set<Integer> numbers = new TreeSet<>();
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Get the number of elements to add
        System.out.print("Enter the number of elements to add: ");
        int numberOfElements = scanner.nextInt();

        // Add elements to the TreeSet based on user input
        for (int i = 0; i < numberOfElements; i++) {
            System.out.print("Enter element #" + (i + 1) + ": ");
            int element = scanner.nextInt();
            numbers.add(element);
        }

        // Display the initial TreeSet (Note: TreeSet orders elements based on their natural order or a comparator)
        System.out.println("Initial TreeSet: " + numbers);

        // Access and manipulate elements (Note: TreeSet does not support direct access or index-based manipulation)
        System.out.println("TreeSet does not support direct access or index-based manipulation.");

        // Remove an element based on user input
        System.out.print("Enter the element to remove: ");
        int elementToRemove = scanner.nextInt();
        if (numbers.contains(elementToRemove)) {
            numbers.remove(elementToRemove);
            System.out.println("Element removed. Updated TreeSet: " + numbers);
        } else {
            System.out.println("Element not found in the TreeSet.");
        }

        // Display the final TreeSet
        System.out.println("Final TreeSet: " + numbers);

        // Close the scanner
        scanner.close();
    }
}
