import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextPermutation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(reader.readLine());
        int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int current = numbers[length - 1];
        int[] portion = new int[0];
        List<Integer> newNumber = new ArrayList<Integer>();

        for (int i = length - 2; i >= 0; i--) {
            if (numbers[i] > current) {
                current = numbers[i];
                continue;
            }
            portion = Arrays.copyOfRange(numbers, i, length);
            break;
        }

        int borderChar = portion[0];

        for (int ch : numbers) {
            if (ch != borderChar) {
                newNumber.add(ch);
            } else break;
        }

        Arrays.sort(portion);
        Integer[] tempArr = new Integer[portion.length];
        for (int i = 0; i < portion.length; i++) {
            tempArr[i] = portion[i];
        }
        int indexOfOldBorderChar = Arrays.asList(tempArr).indexOf(borderChar);
        int newBorderChar = portion[indexOfOldBorderChar + 1];
        List<Integer> newEnding = new ArrayList<Integer>();

        newNumber.add(newBorderChar);

        for (int ch : portion) {
            if (ch != newBorderChar) {
                newNumber.add(ch);
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < newNumber.size(); i++) {
            answer.append(newNumber.get(i));
            if (i != newNumber.size() - 1) {
                answer.append(" ");
            }
        }
        System.out.println(answer);
    }
}
