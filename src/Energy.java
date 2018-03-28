import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Energy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int evenSum = 0;
        int oddSum = 0;
        int energy = 0;
        int coffee = 0;

        int[] numbers = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        for (int number : numbers) {
            if (number % 2 == 0) evenSum += number;
            else oddSum += number;
        }

        if (evenSum > oddSum) System.out.println(evenSum + " energy drinks");
        else if(oddSum > evenSum) System.out.println(oddSum + " cups of coffee");
        else System.out.println(evenSum + " of both");
    }
}
