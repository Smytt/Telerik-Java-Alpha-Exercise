import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LongestIngreasingSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int longestSequence = 1;
        int currentSequence = 1;
        int nextNumber = Integer.parseInt(br.readLine());
        for (int i = 1; i < n; i++) {
            int currentNumber = nextNumber;
            nextNumber = Integer.parseInt(br.readLine());
            if (nextNumber <= currentNumber) {
                currentSequence = 1;
                continue;
            }
            currentSequence++;
            if (currentSequence > longestSequence) {
                longestSequence = currentSequence;
            }
        }

        System.out.println(longestSequence);

    }
}
