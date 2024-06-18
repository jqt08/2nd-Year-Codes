import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class UserEntryArrayDeque {
    public static void main(String[] args) {
        // Create an ArrayDeque of integers
        Deque<Integer> numbers = new ArrayDeque<>();
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Get the number of elements to add
        System.out.print("Enter the number of elements to add: ");
        int numberOfElements = scanner.nextInt();

        // Add elements to the ArrayDeque based on user input
        for (int i = 0; i < numberOfElements; i++) {
            System.out.print("Enter element #" + (i + 1) + ": ");
            int element = scanner.nextInt();
            numbers.add(element);
        }

        // Display the initial ArrayDeque
        System.out.println("Initial ArrayDeque: " + numbers);

        // Access and manipulate elements
        if (!numbers.isEmpty()) {
            int firstElement = numbers.peekFirst();
            System.out.println("First element: " + firstElement);
            // For example, update the value of the first element (just as an example)
            numbers.pollFirst(); // Remove the first element
            System.out.println("Updated ArrayDeque: " + numbers);
        } else {
            System.out.println("ArrayDeque is empty.");
        }

        // Remove an element from the end of the ArrayDeque
        if (!numbers.isEmpty()) {
            int lastElement = numbers.pollLast();
            System.out.println("Last element (removed): " + lastElement);
        } else {
            System.out.println("ArrayDeque is empty.");
        }

        // Display the final ArrayDeque
        System.out.println("Final ArrayDeque: " + numbers);

        // Close the scanner
        scanner.close();
    }
}
