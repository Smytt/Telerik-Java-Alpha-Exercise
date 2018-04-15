import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PasswordsString {

    static void fakeInput() {
        String input = "21\n" +
                "=<>=><==<><<><<<<>>=\n" +
                "871540";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] commands = br.readLine().toCharArray();
        int k = Integer.parseInt(br.readLine());
        StringBuilder password = new StringBuilder();
        ArrayList<StringBuilder> allPasswords = new ArrayList<>();
        if (foundPassword(commands, n, k, password, allPasswords)) {
            System.out.println(allPasswords.get(k-1));
        }
//        allPasswords.forEach(System.out::println);
    }

    private static boolean foundPassword(
            char[] commands, int passwordLength,
            int k, StringBuilder currentPassword, ArrayList<StringBuilder> allPasswords) {

        if (currentPassword.length() == passwordLength) {
            allPasswords.add(currentPassword);
            return allPasswords.size() == k;
        }

        int lastDigit;
        char command = '.';
        if (currentPassword.length() == 0) {
            lastDigit = 9;
        } else {
            lastDigit = currentPassword.charAt(currentPassword.length() - 1) - '0';
            command = commands[currentPassword.length() - 1];
        }

        for (int currentChar = 0; currentChar < 10; currentChar++) {
            currentPassword.append(currentChar);
            switch (command) {
                case '<':
                    if (lastDigit == 1) {
                        removeLast(currentPassword);
                        return false;
                    }
                    if (currentChar == 0 || (currentChar >= lastDigit && lastDigit != 0)) {
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
                    if (currentChar != lastDigit) {
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

    private static void removeLast(StringBuilder currentPassword) {
        currentPassword.setLength(currentPassword.length() - 1);
    }
}
