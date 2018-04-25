package exam.april;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class CountV2 {
    static int count = 0;
    static long target;
}

public class ExpressionsV2 {

    static void fakeInput() {
        String input = "1111\n" +
                "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();
        CountV2.target = Long.parseLong(br.readLine());
        String[] operators = {"*", "+", "-"};
        HashSet<ArrayList<String>> combinations = new HashSet<>();

        findCombinations(number, combinations, new ArrayList<>(), operators);

        System.out.println(CountV2.count);
    }


    private static void findCombinations(String number, HashSet<ArrayList<String>> combinations, ArrayList<String> currentCombination, String[] operators) {
        for (int length = 1; length <= number.length(); length++) {
            String currentNumberStr = number.substring(0, length);
            if (currentNumberStr.charAt(0) == '0' && length != 1) {
                break;
            }
            currentCombination.add(currentNumberStr);

            if (currentNumberStr.length() == number.length()) {
                long result = doMath(new ArrayList<>(currentCombination));
                if (result == CountV2.target) {
                    CountV2.count++;
                }
                combinations.add(new ArrayList<>(currentCombination));
                currentCombination.remove(currentCombination.size() - 1);
                return;
            }

            String newNumber = number.substring(length);
            for (int c = 0; c < operators.length; c++) {
                currentCombination.add(operators[c]);
                findCombinations(newNumber, combinations, currentCombination, operators);
                currentCombination.remove(currentCombination.size() - 1);
            }
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    private static long doMath(ArrayList<String> expression) {
//        System.out.println("----------------------");
//        System.out.println(expression);
        ArrayList<String> afterMultiplication = new ArrayList<>();

        for (int i = 0; i < expression.size(); i += 2) {
            if (i == expression.size() - 1) {
                afterMultiplication.add(expression.get(expression.size() - 1));
                break;
            }
            String currentNumStr = expression.get(i);
            long currentNum = Long.parseLong(currentNumStr);
            if (expression.get(i + 1).charAt(0) != '*') {
                afterMultiplication.add(currentNumStr);
            } else {
                while (true) {
                    currentNum *= Long.parseLong(expression.get(i + 2));
                    i += 2;
                    if (i >= expression.size() - 1) {
                        break;
                    }
                    if (expression.get(i + 1).charAt(0) != '*') {
                        break;
                    }
                }
                afterMultiplication.add(String.valueOf(currentNum));
            }
            if (i >= expression.size() - 1) {
                break;
            }
            afterMultiplication.add(expression.get(i + 1));
        }
//        System.out.println(afterMultiplication);
        long result = Long.parseLong(afterMultiplication.get(0));
        for (int i = 1; i < afterMultiplication.size(); i += 2) {
            if (afterMultiplication.get(i).charAt(0) == '+') {
                result += Long.parseLong(afterMultiplication.get(i + 1));
                continue;
            }
            result -= Long.parseLong(afterMultiplication.get(i + 1));
        }
//        System.out.println(result);
        return result;
    }
}
