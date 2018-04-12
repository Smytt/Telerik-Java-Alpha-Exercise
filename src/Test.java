import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Sudoku {
    public static void main(String[] args) throws IOException {
        int[][] field = new int[9][9];
        readField(field);

        if(solve(field,0,0)) {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    System.out.print(field[row][col]);
                }
                System.out.println();
            }
        }
    }

    public static boolean isSuitableForDigit(int[][] field, int r, int c, int currDigit) {

        // check in the current row if digit already exists
        for (int row = 0; row < 9; row++) {
            if (field[row][c] == currDigit) {
                return false;
            }
        }
        // check in the current column if digit already exists
        for (int col = 0; col < 9; col++) {
            if (field[r][col] == currDigit) {
                return false;
            }
        }

        // check in the current 3x3 box if digit already exists
        int currentBoxRow = r - r % 3;
        int currentBoxCol = c - c % 3;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int currRow = currentBoxRow + row;
                int currCol = currentBoxCol + col;
                if (field[currRow][currCol] == currDigit) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solve(int[][] field,int r,int c) {
        boolean checkForZero = false;
        for (int row = r; row < 9; row++) {
            for (int col = c; col < 9; col++) {
                if (field[row][col] == 0) {
                    checkForZero = true;
                    r = row;
                    c = col;
                    break;
                }
                c = 0;
            }
            if(checkForZero) {
                break;
            }
        }

        // there is no zeros -> the field is filled with digits
        if (!checkForZero) {
            return true;
        }

        for (int currDigit = 1; currDigit <= 9; currDigit++) {
            // check if the current digit can be put on the current [r][c]
            if (isSuitableForDigit(field, r, c, currDigit)) {
                field[r][c] = currDigit;

                if (solve(field,r,c)) {
                    return true;
                }

                field[r][c] = 0;
            }
        }
        return false;
    }

    public static void readField(int[][] field) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int row = 0; row < 9; row++) {
            String[] line = reader.readLine().replace('-', '0').split("");
            for (int col = 0; col < 9; col++) {
                field[row][col] = convert(line[col]);
            }
        }
    }

    private static int convert(String s) {
        int value = 0;
        int flag = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-') {
                flag = -1;
            } else {
                value = value * 10 + (s.charAt(i) - '0');
            }
        }
        if (flag == -1) {
            return -value;
        }
        return value;
    }
}
