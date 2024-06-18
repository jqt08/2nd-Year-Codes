import java.util.Scanner;

public class DigitPlaceValue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input a number (up to 10 digits) -> ");
        long number = scanner.nextLong();

        calculatePlaceValues(number);
    }
    public static void calculatePlaceValues(long number) {
        String[] placeValueNames = {
            "Ones", "Tens", "Hundreds", "Thousands", "Ten Thousands",
            "Hundred Thousands", "Millions", "Ten Millions", "Hundred Millions",
            "Billions"
        };

        long placeValue = 1;
        int digitCount = 0;
        long num = number;

        System.out.println("Place value chart for " + num + ":");

        while (num > 0) {
            long digit = num % 10;
            if (digit > 0) {
                System.out.println(digit + " is in the " + placeValueNames[digitCount] +
                        " place and has a place value of " + digit * placeValue + ".");
            }

            if (digitCount >= placeValueNames.length - 1) {
                System.out.println("Input exceeds the supported digit count.");
                break;
            }

            placeValue *= 10;
            num /= 10;
            digitCount++;
        }
    }
}
