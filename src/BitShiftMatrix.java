import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class BitShiftMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int r = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int coef = Math.max(r, c);
        int curCol = 0;
        int curRow = r - 1;
        ArrayList<Integer> row = new ArrayList<>();
        ArrayList<Integer> col = new ArrayList<>();
        String[] codes = br.readLine().split(" ");
        int sum = 1;
        int cellValue = 1;

        for (String code : codes) {
            row.add(Integer.parseInt(code) / coef);
            col.add(Integer.parseInt(code) % coef);
        }

        boolean[][] matrix = new boolean[r][c];

        for (int i = 0; i < n; i++) {
            int targetRow = row.get(i);
            int targetCol = col.get(i);

            if (targetCol > curCol) {
                int moveRight = targetCol - curCol;
                for (int j = 1; j <= moveRight; j++) {
                    cellValue = cellValue << 1;
                    if (!matrix[curRow][curCol]) {
                        sum += cellValue;
                        matrix[curRow][curCol] = true;
                    }
                    curCol++;
                }
            } else {
                int moveLeft = curCol - targetCol;
                for (int j = 1; j <= moveLeft; j++) {
                    cellValue = cellValue >> 1;
                    if (!matrix[curRow][curCol]) {
                        sum += cellValue;
                        matrix[curRow][curCol] = true;
                    }
                    curCol--;
                }
            }
            if (targetRow < curRow) {
                int moveUp = curRow - targetRow;
                for (int j = 1; j <= moveUp; j++) {
                    cellValue = cellValue << 1;
                    if (!matrix[curRow][curCol]) {
                        sum += cellValue;
                        matrix[curRow][curCol] = true;
                    }
                    curRow--;
                }
            } else {
                int moveDown = targetRow - curRow;
                for (int j = 1; j <= moveDown; j++) {
                    cellValue = cellValue >> 1;
                    if (!matrix[curRow][curCol]) {
                        sum += cellValue;
                        matrix[curRow][curCol] = true;
                    }
                    curRow++;
                }
            }
        }

        System.out.println(sum);
    }
}
