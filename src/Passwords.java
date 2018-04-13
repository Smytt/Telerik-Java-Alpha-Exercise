import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Passwords {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        char[] commands = input[1].toCharArray();
        int k = Integer.parseInt(input[2]);
        int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

//        String password = findPassword(digits, commands, n, k, 0);
//        System.out.println(password);
    }

//    private static String findPassword(int[] digits, char[] commands, int passwordLength, int k, int alreadyFound) {
//
//
//        for (int i = 0; i < 10; i++) {
//
//        }
//    }
}
