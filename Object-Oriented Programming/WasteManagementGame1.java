import java.util.*;

public class WasteManagementGame1 {
    public static final int EASY = 1;
    public static final int HARD = 2;
    public static final int MAX_LIVES = 2;
    public static final int POINTS_PER_CORRECT_ANSWER = 10;

    private static Map<String, Integer> userPoints = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Waste Management Game!");

        String userName = promptForName(scanner);

        while (true) {
            System.out.println("\nSelect difficulty level:");
            System.out.println("1. Easy");
            System.out.println("2. Hard");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            if (choice == 3) {
                System.out.println("Exiting game. Goodbye!");
                break;
            }

            int numberOfQuestions = 2;

            for (int stage = 1; stage <= 2; stage++) {
                int points = GameUtil1.startGame(choice, userName, scanner, numberOfQuestions);

                if (stage == 1 && points == numberOfQuestions * POINTS_PER_CORRECT_ANSWER) {
                    System.out.println("\nProceeding to Part 2!");
                    numberOfQuestions = 3;
                } else {
                    break;
                }
            }

            System.out.println("Congratulations, " + userName + "! You completed both stages. Total Points: " +
                    userPoints.get(userName));
            displayUserPoints();

            promptForNameAndRestart(userName, choice, scanner);
        }
    }

    public static String promptForName(Scanner scanner) {
        System.out.print("Enter your name: ");
        return scanner.nextLine();
    }

    public static Map<String, Integer> getUserPoints() {
        return userPoints;
    }

    public static void promptForNameAndRestart(String userName, int difficulty, Scanner scanner) {
        System.out.println("\nDo you want to try again? (1. Yes, 2. No)");
        int choice = scanner.nextInt();

        if (choice != 1) {
            System.out.println("Exiting game. Goodbye!");
            System.exit(0);
        } else {
            String newUserName = userPoints.containsKey(userName) ? userName : promptForName(scanner);
            main(new String[0]);
        }
    }

    public static void displayUserPoints() {
        System.out.println("\nPoints Summary:");
        for (Map.Entry<String, Integer> entry : userPoints.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " points");
        }
    }
}
