import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordCracker {
    static boolean found = false;
    static void fakeInput() {
        String input =
                "3\n" +
                        "6\n" +
                        "because can do must we what\n" +
                        "wedowhatwemustbecausewecan\n" +
                        "2\n" +
                        "hello planet\n" +
                        "helloworld\n" +
                        "3\n" +
                        "wew wewen edn\n" +
                        "wewen";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) {
        fakeInput();
        Scanner sc = new Scanner(System.in);

        int tests = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < tests; i++) {
            found = false;
            int users = Integer.parseInt(sc.nextLine());
            boolean[] isPresent = new boolean[users];
            String[] passwords = sc.nextLine().split(" ");
            String loginAttempt = sc.nextLine();
            if(!attempt(loginAttempt, passwords, new ArrayList<String>()))
            {
                System.out.println("WRONG PASSWORD");
            }
        }

    }

    private static boolean attempt(String loginAttempt, String[] passwords, ArrayList<String> passList) {
        if (loginAttempt.length() == 0) {
            StringBuilder answer = new StringBuilder();
                    passList.forEach(pass -> answer.append(pass).append(" "));
            System.out.println(answer.substring(0, answer.length() - 1));
            found = true;
            return true;
        }
        for (String password : passwords) {
            if (loginAttempt.indexOf(password) == 0) {
                passList.add(password);
                String newLoginAttempt = loginAttempt.substring(password.length());
                if (attempt(newLoginAttempt, passwords, passList))
                {
                    break;
                }
                passList.remove(passList.size() - 1);

            }
        }
        return found;
    }
}
