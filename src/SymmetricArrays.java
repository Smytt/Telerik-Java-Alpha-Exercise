import java.util.Scanner;

public class SymmetricArrays {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        boolean isSymmetric;

        for (int i = 1; i <= n; i++) {
            isSymmetric = true;
            String[] arr = s.nextLine().split(" ");


            for (int j = 0; j < arr.length / 2; j++) {
                if (!arr[j].equals(arr[arr.length - 1 - j])) {
                    isSymmetric = false;
                    break;
                }
            }
            System.out.println(isSymmetric ? "Yes" : "No");
        }
    }
}
