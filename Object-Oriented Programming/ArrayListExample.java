import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        // Create an ArrayList with type String
        ArrayList<String> names = new ArrayList<>();

        // Create a Scanner object to get user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user to enter values and store them in the ArrayList
        while (true) {
            System.out.print("Enter a name (or 'quit' to exit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                break; // Exit the loop if the user enters 'quit'
            }

            names.add(input);
        }

        // Display the ArrayList
        System.out.println("Names in the ArrayList:");
        for (int i = 0; i < names.size(); i++) {
            System.out.println(i + ": " + names.get(i));
        }

        // Ask the user for an index to retrieve
        System.out.print("Enter an index to retrieve a name: ");
        int indexToRetrieve = scanner.nextInt();

        if (indexToRetrieve >= 0 && indexToRetrieve < names.size()) {
            String retrievedName = names.get(indexToRetrieve);
            System.out.println("Name at index " + indexToRetrieve + ": " + retrievedName);
        } else {
            System.out.println("Invalid index");
        }

        // Close the scanner
        scanner.close();
    }
}

