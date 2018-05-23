import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] n = scanner.nextLine().toCharArray();
        int[] numbers = {Character.getNumericValue(n[0]), Character.getNumericValue(n[1]), Character.getNumericValue(n[2])};
        List<Integer> results = new ArrayList<Integer>();
        results.add(numbers[0] + numbers[1] + numbers[2]);
        results.add(numbers[0] + numbers[1] * numbers[2]);
        results.add(numbers[0] * numbers[1] + numbers[2]);
        results.add(numbers[0] * numbers[1] * numbers[2]);

        System.out.println(Collections.max(results));
    }
}

