import java.util.Scanner;

class CustomBaseConverter {
    int sourceBase;
    String sourceValue;
    int targetBase;

    CustomBaseConverter(int sourceBase, String sourceValue, int targetBase) {
        this.sourceBase = sourceBase;
        this.sourceValue = sourceValue;
        this.targetBase = targetBase;
    }

    public void performConversion() {
        try {
            int decimalValue = Integer.parseInt(sourceValue, sourceBase);
            String targetValue = Integer.toString(decimalValue, targetBase);
            System.out.println("Decimal Conversion Result: " + decimalValue);
            System.out.println("Target Base Conversion Result: " + targetValue);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}

public class CustomBaseConversionApp extends CustomBaseConverter {
    CustomBaseConversionApp(int sourceBase, String sourceValue, int targetBase) {
        super(sourceBase, sourceValue, targetBase);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Source Base (B2, B8): ");
            String sourceBase = scanner.nextLine();
            if (!sourceBase.equals("B2") && !sourceBase.equals("B8")) {
                System.out.println("Invalid input: Source base must be B2 or B8.");
                continue;
            }

            System.out.print("Enter Source Value: ");
            String sourceValue = scanner.nextLine();

            System.out.print("Enter Convert-to-base (B13, B15): ");
            String targetBase = scanner.nextLine();
            if (!targetBase.equals("B13") && !targetBase.equals("B15")) {
                System.out.println("Invalid input: Target base must be B13 or B15.");
                continue;
            }

            CustomBaseConversionApp app = new CustomBaseConversionApp(
                    sourceBase.equals("B2") ? 2 : 8,
                    sourceValue,
                    targetBase.equals("B13") ? 13 : 15
            );
            app.performConversion();

            System.out.print("Try Again? [Y/N]: ");
            String tryAgain = scanner.nextLine();
            if (!tryAgain.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }
}
