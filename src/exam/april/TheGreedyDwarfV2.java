package exam.april;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheGreedyDwarfV2 {
    static void fakeInput() {
        String input = "3 3\n" +
                "10 10 0\n" +
                "10 10 10\n" +
                "10 10 10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dimensions = br.readLine().split(" ");
        int r = Integer.parseInt(dimensions[0]);
        int c = Integer.parseInt(dimensions[1]);
        int currentRow = 0;
        int currentCol = 0;
        int sum = 0;
        int maxValue = 0;
        int[][] field = new int[r][c];

        for (int i = 0; i < r; i++) {
            String charRow = br.readLine();
            String[] chars = charRow.split(" ");
            for (int j = 0; j < chars.length; j++) {
                int currentChar = Integer.parseInt(chars[j]);
                field[i][j] = currentChar;
                if (currentChar == 0) {
                    currentRow = i;
                    currentCol = j;
                }
            }
        }

        int[] dCol = {-1, +1, 0, 0};
        int[] dRow = {0, 0, -1, +1};

        while (true) {
            maxValue = 0;
            int nextR = 0;
            int nextC = 0;

            for (int i = 0; i < dRow.length; i++) {
                int testRow = currentRow + dRow[i];
                int testCol = currentCol + dCol[i];

                if (!isValid(r, c, testRow, testCol)) {
                    continue;
                }

                int testValue = field[testRow][testCol];

                if (testValue > maxValue) {
                    maxValue = testValue;
                    nextR = testRow;
                    nextC = testCol;
                }
            }

            if (maxValue == 0) {
                break;
            }


            sum++;
            field[nextR][nextC]--;
            currentRow = nextR;
            currentCol = nextC;

        }

        System.out.println(sum);

    }

    private static boolean isValid(int r, int c, int testRow, int testCol) {
        return 0 <= testRow && 0 <= testCol && testRow < r && testCol < c;
    }
}
