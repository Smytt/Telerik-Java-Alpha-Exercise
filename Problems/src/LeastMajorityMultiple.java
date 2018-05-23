import java.util.Scanner;

public class LeastMajorityMultiple {
    public static void main(String[] args) {
        int[] numbers = new int[5];
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = s.nextInt();;
        }
        int count = 0;
        int lmm = 0;
        while (count < 3) {
            lmm++;
            count = 0;

            for (int i = 0; i < numbers.length; i++) {
                if (lmm % numbers[i] == 0) {
                    count++;
                }
            }
        }

        System.out.println(lmm);
    }
}
