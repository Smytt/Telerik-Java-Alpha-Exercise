import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<String> sequence = new ArrayList<String>();

        for (int i = 1; i <= N; i++) {
            if (isPrime(i)) {
                sequence.add("1");
                System.out.println(String.join("", sequence));
            } else sequence.add("0");
        }
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= n / 2.0; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
