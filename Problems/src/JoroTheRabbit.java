import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JoroTheRabbit {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] stringNumbers = br.readLine().split(", ");
        int longestSequence = 0;

        // initial map
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (String num : stringNumbers) {
            numbers.add(Integer.parseInt(num));
        }

        for (int i = 0; i < numbers.size(); i++) {
            for (int hop = 1; hop < numbers.size(); hop++) {
                int currentSequence = 0;
                int index = i;
                boolean continueHopping = true;

                while (continueHopping) {
                    int currentNumber = numbers.get(index);
                    currentSequence++;
                    index += hop;
                    index = index % numbers.size();
                    if (currentNumber >= numbers.get(index)) {
                        continueHopping = false;
                    }
                }

                if (currentSequence > longestSequence) {
                    longestSequence = currentSequence;
                }
            }
        }
        System.out.println(longestSequence);
    }
}