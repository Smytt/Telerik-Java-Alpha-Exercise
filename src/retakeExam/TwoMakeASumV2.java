package retakeExam;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoMakeASumV2 {

    static void fakeInput() {
        String input =
                "1 2 4 4\n" +
                        "4 5 8";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        int total = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbersStr = br.readLine().split(" ");
        String[] sumsStr = br.readLine().split(" ");

        int[] numbers = new int[numbersStr.length];
        int[] sums = new int[sumsStr.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersStr[i]);
        }
        for (int i = 0; i < sums.length; i++) {
            sums[i] = Integer.parseInt(sumsStr[i]);
        }

        for (int sum = 0; sum < sums.length; sum++) {
            boolean breakFirst = false;
            for (int first = 0; first < numbers.length - 1; first++) {
                if (numbers[first] > sums[sum]) break;
                for (int second = first + 1; second < numbers.length; second++) {
                    if (numbers[first] + numbers[second] == sums[sum]) {
                        total++;
                        breakFirst = true;
                        break;
                    }
                    if (numbers[first] + numbers[second] > sums[sum]) {
                        break;
                    }
                }
                if (breakFirst) break;
            }
        }

        System.out.println(total);
    }
}