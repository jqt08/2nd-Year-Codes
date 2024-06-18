import java.util.PriorityQueue;
import java.util.Scanner;

public class UserEntryPriorityQueue {
    public static void main(String[] args) {
        // Create a PriorityQueue of integers
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Get the number of elements to add
        System.out.print("Enter the number of elements to add: ");
        int numberOfElements = scanner.nextInt();

        // Add elements to the PriorityQueue based on user input
        for (int i = 0; i < numberOfElements; i++) {
            System.out.print("Enter element #" + (i + 1) + ": ");
            int element = scanner.nextInt();
            numbers.add(element);
        }

        // Display the initial PriorityQueue (Note: PriorityQueues do not guarantee a specific order)
        System.out.println("Initial PriorityQueue: " + numbers);

        // Access and manipulate elements (Note: PriorityQueue does not provide direct access or index-based manipulation)
        System.out.println("PriorityQueue does not support direct access or index-based manipulation.");

        // Remove the smallest element (highest priority) from the PriorityQueue
        if (!numbers.isEmpty()) {
            int smallestElement = numbers.poll();
            System.out.println("Smallest element (removed): " + smallestElement);
        } else {
            System.out.println("PriorityQueue is empty.");
        }

        // Display the remaining PriorityQueue elements
        System.out.println("Updated PriorityQueue: " + numbers);

        // Close the scanner
        scanner.close();
    }
}
