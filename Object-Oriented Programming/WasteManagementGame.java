import java.util.*;

public class WasteManagementGame {
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
            int difficultyChoice = scanner.nextInt();

            if (difficultyChoice == 3) {
                System.out.println("Exiting game. Goodbye!");
                break;
            }

            int numberOfQuestions = (difficultyChoice == EASY) ? 3 : 5;
            int lives = MAX_LIVES;

            boolean stage1Completed = false;

            for (int stage = 1; stage <= 2; stage++) {
                if (stage == 2 && !stage1Completed) {
                    System.out.println("You need to complete Stage 1 first. Please select Stage 1.");
                    stage--;
                    continue;
                }

                System.out.println("\nSelect stage:");
                System.out.println("1. Stage 1 (" + numberOfQuestions + " questions)");
                System.out.println("2. Stage 2 (5 questions)");

                int stageChoice = scanner.nextInt();

                int points = GameUtil.startGame(difficultyChoice, userName, scanner);

                if (stageChoice == 1) {
                    stage1Completed = true;
                }

                if (stage == 1 && points == numberOfQuestions * POINTS_PER_CORRECT_ANSWER) {
                    System.out.println("\nProceeding to Stage 2!");
                    numberOfQuestions = 5;
                } else {
                    break;
                }
            }

            System.out.println("Congratulations, " + userName + "! You completed both stages. Total Points: " +
                    userPoints.get(userName));
            displayUserPoints();

            promptForNameAndRestart(userName, difficultyChoice, scanner);
        }
    }

    public static String promptForName(Scanner scanner) {
        System.out.print("Enter your name: ");
        return scanner.next();
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
