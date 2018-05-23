package retakeExam;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class Count {
    static int count = 0;
}

public class ExpressionsAgain {
    static void fakeInput() {
        String input = "1000001\n" +
                "101";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();
        long target = Long.parseLong(br.readLine());
        char[] operators = {'*', '+', '-'};
        HashSet<ArrayList<Long>> combinations = new HashSet<>();

        findCombinations(number, combinations, new ArrayList<>());

        combinations.forEach(combination -> {
            System.out.println(combination);
            doCalculations(combination, operators, target);
        });

        System.out.println(Count.count);
    }

    private static void doCalculations(ArrayList<Long> combination, char[] operators, long target) {
        if (combination.size() == 1) {
            if (combination.get(0) == target) {
                Count.count++;
            }
            return;
        }
        for (int i = 0; i < combination.size() - 1; i++) {
            for (int j = 0; j < 3; j++) {
                ArrayList<Long> alteredCombination = new ArrayList<>(combination);
                switch (operators[j]) {
                    case '*': {
                        alteredCombination.set(i + 1, alteredCombination.get(i) * alteredCombination.get(i + 1));
                        alteredCombination.remove(i);
                        doCalculations(alteredCombination, operators, target);
                        break;
                    }
                    case '+': {
                        alteredCombination.set(i + 1, alteredCombination.get(i) + alteredCombination.get(i + 1));
                        alteredCombination.remove(i);
                        doCalculations(alteredCombination, operators, target);
                        break;
                    }
                    case '-': {
                        alteredCombination.set(i + 1, alteredCombination.get(i) - alteredCombination.get(i + 1));
                        alteredCombination.remove(i);
                        doCalculations(alteredCombination, operators, target);
                        break;
                    }
                }
            }
            return;
        }

    }

    private static void findCombinations(String number, HashSet<ArrayList<Long>> combinations, ArrayList<Long> currentCombination) {
        for (int length = 1; length <= number.length(); length++) {
            String currentNumberStr = number.substring(0, length);
            if (currentNumberStr.charAt(0) == '0' && length != 1) {
                break;
            }
            long currentNumber = Long.parseLong(currentNumberStr);
            currentCombination.add(currentNumber);

            if (currentNumberStr.length() == number.length()) {
                combinations.add(new ArrayList<>(currentCombination));
                currentCombination.remove(currentCombination.size() - 1);
                return;
            }

            String newNumber = number.substring(length);
            findCombinations(newNumber, combinations, currentCombination);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

}
