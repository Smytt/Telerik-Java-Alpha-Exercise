import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ZigZag {

    static void fakeInput() {
        String input = "5\n" +
                "6";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) {
        fakeInput();
        Scanner s = new Scanner(System.in);
        long nMax = s.nextInt();
        long mMax = s.nextInt();
        long sum = 0;
        long coef;

        for (int n = 0; n < nMax; n++) {
            for (int m = 0; m < mMax; m++) {
                if (m % 2 != n % 2) continue;
                if (m == 0 || m == mMax - 1 || n == 0 || n == nMax - 1) {
                    coef = 1;
                } else {
                    coef = 2;
                }
                if (n % 2 == 0) {
                    if (m % 2 == 0) {
                        sum = sum + ((n + m) * 3 + 1) * coef;
                    }
                } else {
                    if (m % 2 == 1) {
                        sum = sum + ((n + m) * 3 + 1) * coef;
                    }
                }
            }
        }

        System.out.println(sum);

    }
}
