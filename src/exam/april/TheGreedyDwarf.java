package exam.april;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheGreedyDwarf {
    static void fakeInput() {
        String input = "4 3\n" +
                "3 2 4\n" +
                "2 0 3\n" +
                "1 1 5\n" +
                "2 2 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String dimensions = br.readLine();
        int r = dimensions.charAt(0) - '0';
        int c = dimensions.charAt(2) - '0';
        int currentRow = 0;
        int currentCol = 0;
        int sum = 0;
        ArrayList<ArrayList<Integer>> field = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            String charRow = br.readLine();
            String[] chars = charRow.split(" ");
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < chars.length; j++) {
                int currentChar = Integer.parseInt(chars[j]);
                row.add(currentChar);
                if (currentChar == 0) {
                    currentRow = i;
                    currentCol = j;
                }
            }
            field.add(row);
        }

        while (true) {
            int leftValue = 0;
            int rightValue = 0;
            int upValue = 0;
            int downValue = 0;
            int maxValue = 0;


            if (currentCol > 0) {
                leftValue = field.get(currentRow).get(currentCol - 1);
            }
            if (currentCol < c - 1) {
                rightValue = field.get(currentRow).get(currentCol + 1);
            }
            if (currentRow > 0) {
                upValue = field.get(currentRow - 1).get(currentCol);
            }
            if (currentRow < r - 1) {
                downValue = field.get(currentRow + 1).get(currentCol);
            }

            maxValue = Math.max(leftValue, Math.max(rightValue, Math.max(upValue, downValue)));

            if (maxValue == 0) {
                break;
            }

            if (maxValue == leftValue) {
                currentCol--;
            } else if (maxValue == rightValue) {
                currentCol++;
            } else if (maxValue == upValue) {
                currentRow--;
            } else if (maxValue == downValue) {
                currentRow++;
            }

            sum++;
            int currentCoinsInCell = field.get(currentRow).get(currentCol);
            field.get(currentRow).set(currentCol, currentCoinsInCell - 1);
        }

        System.out.println(sum);
    }
}
