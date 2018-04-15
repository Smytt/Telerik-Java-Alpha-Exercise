import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class K {
    static int k = 0;
}
public class PasswordsV2 {

    static void fakeInput() {
        String input = "7\n" +
                "<=>>=<\n" +
                "23";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String commands = br.readLine();
        K.k = Integer.parseInt(br.readLine());
        StringBuilder password = new StringBuilder();
        if (foundPassword(commands, n, K.k, password)) {
            System.out.println(password);
        }
//        allPasswords.forEach(System.out::println);
    }

    private static boolean foundPassword(
            String commands, int passwordLength,
            int k, StringBuilder currentPassword) {

        //check if password has reached target length and if it's k-th variation
        if (currentPassword.length() == passwordLength) {
            K.k--;
            return K.k==0;
        }

        //get first digit
        if (currentPassword.length() == 0) {
            for (int i = 0; i < 10; i++) {
                currentPassword.append(i);
                if (!foundPassword(commands, passwordLength, K.k, currentPassword)) {
                    removeLast(currentPassword);
                    continue;
                }
                return true;
            }
            return false;
        }

        char command = commands.charAt(currentPassword.length() - 1);
        int lastDigit = currentPassword.charAt(currentPassword.length() - 1) - '0';
        int start, end;
        if (command == '<') {
            if (lastDigit == 0) {
                start = 1;
                end = 9;
            } else {
                start = 1;
                end = lastDigit - 1;
            }
            for (int i = start; i < end + 1; i++) {
                currentPassword.append(i);
                if (!foundPassword(commands, passwordLength, K.k, currentPassword)) {
                    removeLast(currentPassword);
                    continue;
                }
                return true;
            }
            return false;
        }
        else if (command == '>') {
            if(lastDigit == 0) {
                return false;
            }
            start = lastDigit + 1;
            end = 9;
            currentPassword.append(0);
            if (!foundPassword(commands, passwordLength, K.k, currentPassword)) {
                removeLast(currentPassword);
                for (int i = start; i < end + 1; i++) {
                    currentPassword.append(i);
                    if (!foundPassword(commands, passwordLength, K.k, currentPassword)) {
                        removeLast(currentPassword);
                        continue;
                    }
                    return true;
                }
                return false;
            }
            return true;
        }
        else {
            currentPassword.append(lastDigit);
            if(!foundPassword(commands, passwordLength, K.k, currentPassword)){
                removeLast(currentPassword);
                return false;
            }
            return true;
        }
    }

    private static void removeLast(StringBuilder currentPassword) {
        currentPassword.setLength(currentPassword.length() - 1);
    }
}
