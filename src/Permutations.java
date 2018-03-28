import java.util.Scanner;

public class Permutations {
    public static void main(String[] args) {
        int total = (new Scanner(System.in)).nextInt();

        for (int i = 1; i <= total; i++) {
            String str = "" + i;
            permutate(i, total, str);
        }
    }

    private static void permutate(int number, int total, String str) {
        if ((str.length() + 1) / 2 == total) {
            System.out.println(str);
            return;
        }
        String oldStr = str;
        for (int i = 1; i <= total; i++) {
            if (!oldStr.contains(String.valueOf(i))) {
                str = oldStr + " " + i;
                permutate(i, total, str);
            }
        }
    }
}
