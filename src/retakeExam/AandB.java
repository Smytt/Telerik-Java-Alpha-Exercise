package retakeExam;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class AandB {
    static void fakeInput() {
        String input =
                "4\n" +
                        "7 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) {
        fakeInput();
        Scanner sc = new Scanner(System.in);

        int length = Integer.parseInt(sc.nextLine());
        String[] numbers = sc.nextLine().split(" ");
        Arrays.sort(numbers);

        findCombinations(numbers, length, new StringBuilder());
    }

    private static void findCombinations(String[] numbers, int length, StringBuilder combination) {
        if(combination.length() == length) {
            System.out.println(combination);
            return;
        }
        for (String number: numbers) {
            combination.append(number);
            findCombinations(numbers, length, combination);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
}
