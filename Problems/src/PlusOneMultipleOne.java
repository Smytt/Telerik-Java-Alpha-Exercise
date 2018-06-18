import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PlusOneMultipleOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Integer> steps = new ArrayList<>();
        steps.add(n);
        int index = 0;

        while (steps.size() < m) {

            Integer sum = steps.get(index);
            steps.add(sum + 1);
            steps.add(sum * 2 + 1);
            steps.add(sum + 2);

            index++;
        }
        System.out.println(steps.get(m - 1));
    }
}