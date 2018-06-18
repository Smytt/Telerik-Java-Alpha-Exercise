package dsa_retake;

import java.util.Arrays;
import java.util.Scanner;

public class GreaterMoney {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int[] bag1 = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] bag2 = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();



        for (int i = 0; i < bag1.length; i++) {
            int money = bag1[i];
            boolean startLooking = false;
            boolean found = false;
            for (int j = 0; j< bag2.length; j++) {
                if (startLooking && bag2[j] > money) {
                    sb.append(bag2[j]);
                    sb.append(",");
                    found = true;
                    break;
                }
                if (bag2[j] == money) {
                    startLooking = true;
                }
            }
            if(!found) {
                sb.append(-1);
                sb.append(",");
            }
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }
}
