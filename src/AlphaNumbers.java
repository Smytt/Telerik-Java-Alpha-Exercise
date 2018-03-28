import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class AlphaNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 7; i++) {
            String input = br.readLine();
            int[] numbers = Arrays.stream(input.split("")).mapToInt(Integer::parseInt).toArray();

            int biggestDifference = Math.max(
                    Math.abs(numbers[0] - numbers[1]),
                    Math.max(Math.abs(numbers[0] - numbers[2]), Math.abs(numbers[2] - numbers[1]))
            );

            int lastDigitOfSum = IntStream.of(numbers).sum() % 10;

            if (biggestDifference > lastDigitOfSum) {
                System.out.println(input);
            }

        }
    }
}
