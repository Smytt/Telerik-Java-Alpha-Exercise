package retakeExam;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TwoMakeASum {

    static void fakeInput() {
        String input =
                "1 2 4\n" +
                        "1 2 3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        //fakeInput();
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

        int[] maxSumsByIndex = new int[numbers.length];
        int lastIndex = 0;
        for (int i = 1; i < numbers.length; i++) {
            maxSumsByIndex[i] = numbers[i] + numbers[i - 1];
        }

        for (int i = 0; i < sums.length; i++) {
            boolean stop = true;
            if (i > 0 && sums[i] == sums[i - 1]) {
                total++;
                continue;
            }
            boolean shouldBreak = false;
            for (int j = lastIndex; j < maxSumsByIndex.length; j++) {
                if (maxSumsByIndex[j] < sums[i]) {
                    stop = true;
                    continue;
                }
                stop = false;
                for (int k = j; k < numbers.length; k++) {

                    if (numbers[k] > sums[i]) {
                        shouldBreak = true;
                        break;
                    }

                    for (int m = 0; m < numbers.length; m++) {
                        if (k == m) {
                            continue;
                        }

                        if (numbers[k] + numbers[m] > sums[i]) {
                            break;
                        }

                        if (numbers[k] + numbers[m] == sums[i]) {
                            total++;
                            shouldBreak = true;
                            break;
                        }
                    }
                    if (shouldBreak) {
                        break;
                    }
                }
                if (shouldBreak) {
                    break;
                }
            }
            if (stop) {
                break;
            }
        }

        System.out.println(total);
    }
}