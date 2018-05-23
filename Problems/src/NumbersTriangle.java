import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumbersTriangle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            printLine(i);
        }

        for (int i = n - 1; i >= 1; i--) {
            printLine(i);
        }

    }

    static void printLine(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
