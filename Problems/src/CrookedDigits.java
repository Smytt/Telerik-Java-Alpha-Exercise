import java.util.Scanner;

public class CrookedDigits {

    private int reduced = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 0;

        for (char ch : scanner.nextLine().toCharArray()) {
            if (ch != '.' && ch != '-') {
                N += (ch - '0');
            }
        }

        CrookedDigits obj = new CrookedDigits();

        N = obj.reduce(N);

        System.out.println((int) N);
    }

    private int reduce(int N) {
        if (N < 10) return N;
        N = sum(N);
        reduced = 0;
        return reduce(N);
    }

    private int sum(int number) {
        reduced += number % 10;
        if (number < 10) return reduced;
        return sum(number /= 10);
    }


}
