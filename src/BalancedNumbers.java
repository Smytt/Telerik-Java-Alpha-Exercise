import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BalancedNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        int sum = 0;
        while (isBalanced(n)) {
            sum += n;
            n = Integer.parseInt(in.readLine());
        }
        System.out.println(sum);
    }

    private static boolean isBalanced(int n) {
        return n >= 100 && n <= 999 && n / 100 + n % 10 == (n / 10) % 10;
    }
}
