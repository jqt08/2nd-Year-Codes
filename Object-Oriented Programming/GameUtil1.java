import java.util.*;

public class GameUtil1 {
    public static int startGame(int difficulty, String userName, Scanner scanner, int numberOfQuestions) {
        int lives = WasteManagementGame1.MAX_LIVES;
        int points = 0;
        boolean gameOver = false;

        List<String> usedWaste = new ArrayList<>();

        for (int i = 0; i < numberOfQuestions; i++) {
            String wasteType = generateRandomWaste(difficulty, usedWaste);
            usedWaste.add(wasteType);

            displayChallenge(wasteType, difficulty);

            String userAnswer = getUserAnswer(scanner);
            if (isAnswerCorrect(wasteType, userAnswer, difficulty)) {
                points += WasteManagementGame1.POINTS_PER_CORRECT_ANSWER;
                System.out.println("Correct! Points: " + points);
            } else {
                lives--;
                System.out.println("Wrong! Lives remaining: " + lives);

                if (lives == 0) {
                    gameOver = true;
                    System.out.println("Game Over! Total Points: " + points);
                    break;
                }
            }
        }

        if (!gameOver) {
            System.out.println("Congratulations, " + userName + "! You completed all challenges. Total Points: " + points);
            WasteManagementGame1.getUserPoints().put(userName, WasteManagementGame1.getUserPoints().getOrDefault(userName, 0) + points);
            displayUserPoints();
        }

        return points;
    }

    private static String generateRandomWaste(int difficulty, List<String> usedWaste) {
        Random random = new Random();
        String[] biodegradableWastes = {"Apple Core", "Paper Towel", "Banana Peel", "Grass Clippings", "Eggshells"};
        String[] nonBiodegradableWastes = {"Plastic Bag", "Aluminum Foil", "Glass Bottle", "Styrofoam Cup", "Cigarette Butt"};
        String[] recyclableWastes = {"Plastic Bottle", "Aluminum Can", "Glass Bottle", "Electronic Waste", "Plastic"};
        String[] nonRecyclableWastes = {"Newspaper", "Cardboard", "Food Waste", "Tissues", "Old Monitor", "Cellphone"};
        String[] compostWastes = {"Paper", "Coffee Grounds", "Napkins"};

        List<String> availableWaste;
        switch (difficulty) {
            case WasteManagementGame.EASY:
                availableWaste = new ArrayList<>(Arrays.asList(biodegradableWastes));
                availableWaste.addAll(Arrays.asList(nonBiodegradableWastes));
                break;
            case WasteManagementGame.HARD:
                availableWaste = new ArrayList<>(Arrays.asList(recyclableWastes));
                availableWaste.addAll(Arrays.asList(nonRecyclableWastes));
                availableWaste.addAll(Arrays.asList(compostWastes));
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level");
        }

        availableWaste.removeAll(usedWaste);

        if (availableWaste.isEmpty()) {
            System.out.println("All waste materials used. Restarting game...");
            usedWaste.clear();
            return generateRandomWaste(difficulty, usedWaste);
        }

        return availableWaste.get(random.nextInt(availableWaste.size()));
    }

    private static void displayChallenge(String wasteType, int difficulty) {
        System.out.println("\nWhere should <" + wasteType + "> be thrown?");

        switch (difficulty) {
            case WasteManagementGame.EASY:
                System.out.println("Bins: 1. Biodegradable  2. Non-biodegradable");
                break;
            case WasteManagementGame.HARD:
                System.out.println("Bins: 1. Recyclable  2. Non-recyclable  3. Compost  4. Hazardous  5. Electrical");
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level");
        }

        System.out.print("Your answer: ");
    }

    private static String getUserAnswer(Scanner scanner) {
        return scanner.next();
    }

    private static boolean isAnswerCorrect(String wasteType, String userAnswer, int difficulty) {
        if (difficulty == WasteManagementGame.EASY) {
            if (isBiodegradable(wasteType) && userAnswer.equals("1")) {
                return true;
            } else return !isBiodegradable(wasteType) && userAnswer.equals("2");
        } else {
            if (userAnswer.equals("1")) {
                return Arrays.asList("Plastic Bottle", "Aluminum Can", "Glass Bottle", "Plastic").contains(wasteType);
            } else if (userAnswer.equals("2")) {
                return Arrays.asList("Newspaper", "Cardboard", "Food Waste", "Tissues", "Styrofoam Cup").contains(wasteType);
            } else if (userAnswer.equals("3")) {
                return Arrays.asList("Paper", "Coffee Grounds", "Napkins").contains(wasteType);
            } else if (userAnswer.equals("4")) {
                return wasteType.equals("Cigarette Butt");
            } else return userAnswer.equals("5") && Arrays.asList("Old Monitor", "Cellphone").contains(wasteType);
        }
    }

    private static boolean isBiodegradable(String wasteType) {
        String[] biodegradableWastes = {"Apple Core", "Paper Towel", "Banana Peel", "Grass Clippings", "Eggshells"};
        return Arrays.asList(biodegradableWastes).contains(wasteType);
    }

    private static void promptForNameAndRestart(String userName, int difficulty, Scanner scanner) {
        System.out.println("\nDo you want to try again? (1. Yes, 2. No)");
        int choice = scanner.nextInt();

        if (choice != 1) {
            System.out.println("Exiting game. Goodbye!");
            System.exit(0);
        } else {
            String newUserName = WasteManagementGame.getUserPoints().containsKey(userName) ? userName : WasteManagementGame.promptForName(scanner);
            WasteManagementGame.main(new String[0]);
        }
    }

    public static void displayUserPoints() {
        System.out.println("\nPoints Summary:");
        for (Map.Entry<String, Integer> entry : WasteManagementGame1.getUserPoints().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " points");
        }
    }
}