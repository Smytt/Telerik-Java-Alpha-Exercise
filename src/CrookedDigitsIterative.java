import java.util.Scanner;

public class CrookedDigitsIterative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int N = 0;

        for (char ch : str.toCharArray()) {
            if (ch != '.' && ch != '-') {
                N += (ch - '0');
            }
        }

        while (N > 9) {
            int newNumber = (int) N;
            N = 0;

            while (newNumber > 0) {
                N += newNumber % 10;
                newNumber /= 10;
            }
        }

        System.out.println((int) N);
    }
}
