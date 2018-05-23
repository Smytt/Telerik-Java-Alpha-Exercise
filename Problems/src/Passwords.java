import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Passwords {

    static void fakeInput() {
        String input = "25\n" +
                "<><>===<<=>><=<>==>>><<>\n" +
                "767508";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] commands = br.readLine().toCharArray();
        int k = Integer.parseInt(br.readLine());
        ArrayList<Integer> password = new ArrayList<>();
        ArrayList<ArrayList<Integer>> allPasswords = new ArrayList<>();
        if (foundPassword(commands, n, k, password, allPasswords)) {

            allPasswords.get(k - 1).forEach(System.out::print);
            System.out.println();

        }
//        allPasswords.forEach(System.out::println);
    }

    private static boolean foundPassword(
            char[] commands, int passwordLength,
            int k, ArrayList<Integer> currentPassword, ArrayList<ArrayList<Integer>> allPasswords) {

        if (currentPassword.size() == passwordLength) {
            allPasswords.add(new ArrayList<>(currentPassword));
            return allPasswords.size() == k;
        }

        int lastDigit;
        char command = '.';
        if (currentPassword.size() == 0) {
            lastDigit = 9;
        } else {
            lastDigit = currentPassword.get(currentPassword.size() - 1);

            command = commands[currentPassword.size() - 1];
        }

        for (int currentChar = 0; currentChar < 10; currentChar++) {
            currentPassword.add(currentChar);
            switch (command) {
                case '<':
                    if (lastDigit == 1) {
                        removeLast(currentPassword);
                        return false;
                    }
                    if (currentChar == 0 || (currentChar >= lastDigit && lastDigit != 0 )) {
                        removeLast(currentPassword);
                        continue;
                    }
                    if (!foundPassword(commands, passwordLength, k, currentPassword, allPasswords)) {
                        removeLast(currentPassword);
                        continue;
                    }
                    break;
                case '>':
                    if (lastDigit == 0) {
                        removeLast(currentPassword);
                        return false;
                    }
                    if (currentChar > 0 && currentChar <= lastDigit) {
                        removeLast(currentPassword);
                        continue;
                    }
                    if (!foundPassword(commands, passwordLength, k, currentPassword, allPasswords)) {
                        removeLast(currentPassword);
                        continue;
                    }
                    break;
                case '=':
                    if(currentChar != lastDigit) {
                        removeLast(currentPassword);
                        continue;
                    }
                    if (!foundPassword(commands, passwordLength, k, currentPassword, allPasswords)) {
                        removeLast(currentPassword);
                        continue;
                    }
                    break;
                default:
                    if (!foundPassword(commands, passwordLength, k, currentPassword, allPasswords)) {
                        removeLast(currentPassword);
                        continue;
                    }
                    break;
            }
            return true;

        }
        return false;
    }

    private static void removeLast(ArrayList<Integer> currentPassword) {
        currentPassword.remove(currentPassword.size() - 1);
    }
}
