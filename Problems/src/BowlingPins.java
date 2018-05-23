import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BowlingPins {
    static void fakeInput() {
        String input =
                "1\n" +
                        "54\n" +
                        "IXXIXIIXIIXIIIIIIIIXXIXIIXIIXIIIIIIIIXXIXIIXIIXIIIIIII\n" +
                        "4\n" +
                        "XIIX\n" +
                        "5\n" +
                        "IIXII\n" +
                        "5\n" +
                        "IIIII";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();

        Scanner sc = new Scanner(System.in);

        int test = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < test; i++) {
            int pinsQty = Integer.parseInt(sc.nextLine());
            StringBuilder pins = new StringBuilder().append(sc.nextLine());

            if (outcome(pins)) {
                System.out.println("WIN");
            } else {
                System.out.println("LOSE");
            }
        }
    }

    private static boolean outcome(StringBuilder pins) {
        return player1(pins);
    }

    private static boolean player1(StringBuilder pins) {
        System.out.println(pins.toString());
        for (int indexOfI = 0; indexOfI < pins.length(); indexOfI++) {
            if (pins.charAt(indexOfI) == 'X') {
                continue;
            }
            StringBuilder newPins = new StringBuilder();
            String before = pins.substring(0, indexOfI);
            newPins.append(before)
                    .append('X')
                    .append(pins.substring(indexOfI + 1));
            if (!player2(newPins)) {
                return true;
            }

            if (canKnockTwo(pins, indexOfI)) {
                newPins.setLength(0);
                newPins.append(before)
                        .append("XX")
                        .append(pins.substring(indexOfI + 2));
                if (!player2(newPins)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean player2(StringBuilder pins) {
        System.out.println(pins.toString());

        for (int indexOfI = 0; indexOfI < pins.length(); indexOfI++) {
            if (pins.charAt(indexOfI) == 'X') {
                continue;
            }
            StringBuilder newPins = new StringBuilder();
            String before = pins.substring(0, indexOfI);
            newPins.append(before)
                    .append('X')
                    .append(pins.substring(indexOfI + 1));
            if (!player1(newPins)) {
                return true;
            }

            if (canKnockTwo(pins, indexOfI)) {
                newPins.setLength(0);
                newPins.append(before)
                        .append("XX")
                        .append(pins.substring(indexOfI + 2));
                if (!player1(newPins)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean canKnockTwo(StringBuilder pins, int firstPinIndex) {
        return firstPinIndex != pins.length() - 1 && pins.charAt(firstPinIndex + 1) == 'I';
    }
}
