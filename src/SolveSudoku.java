import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SolveSudoku {
    static void fakeInput() {
        String input = "53--7----\n" +
                "6--195---\n" +
                "-98----6-\n" +
                "8---6---3\n" +
                "4--8-3--1\n" +
                "7---2---6\n" +
                "-6----28-\n" +
                "---419--5\n" +
                "----8--79";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<Character>> sudoku = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            String row = br.readLine();
            List<Character> chars = new ArrayList<Character>();
            for (char c : row.toCharArray()) {
                chars.add(c);
            }
            sudoku.add(chars);
        }

        solveSudoku(sudoku);

        sudoku.forEach(row -> {
            row.forEach(System.out::print);
            System.out.println();
        });

    }

    static boolean solveSudoku(List<List<Character>> sudoku) {
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            List<Character> row = sudoku.get(rowIndex);
            int emptyIndex = row.indexOf('-');
            if (emptyIndex == -1) {
                continue;
            }
            ArrayList<Character> possibleChars = findPossibleCharsForCell(sudoku, rowIndex, emptyIndex);
            if (possibleChars.size() == 0) {
                return false;
            }
            for (Character possibleChar : possibleChars) {
                row.set(emptyIndex, possibleChar);
                if (!solveSudoku(sudoku)) {
                    row.set(emptyIndex, '-');
                    return false;
                }
            }
        }

        return true;
    }

    private static ArrayList<Character> findPossibleCharsForCell(List<List<Character>> sudoku, int rowIndex, int emptyIndex) {
        ArrayList<Character> possibleChars = new ArrayList<>();
        List<Character> row = sudoku.get(rowIndex);

        for (char i = '1'; i <= '9'; i++) {
            if (row.contains(i)) {
                continue;
            }
            if (!canBeInColumnAndSubsquare(sudoku, rowIndex, i)) {
                continue;
            }
            possibleChars.add(i);
        }

        return possibleChars;
    }

    private static boolean canBeInColumnAndSubsquare(List<List<Character>> sudoku, int rowIndex, char i) {
        List<Character> row = sudoku.get(rowIndex);
        int emptyIndex = row.indexOf('-');

        //check if current char exists in column
        for (List<Character> checkRow : sudoku) {
            if (checkRow.get(emptyIndex) == i) {
                return false;
            }
        }

        //check if current char exists in square
        int smallSquareStartRow = (rowIndex / 3) * 3;
        int smallSquareStartCol = (emptyIndex / 3) * 3;

        for (int smallRow = smallSquareStartRow; smallRow < smallSquareStartRow + 3; smallRow++) {
            for (int smallCol = smallSquareStartCol; smallCol < smallSquareStartCol + 3; smallCol++) {
                char checkChar = sudoku.get(smallRow).get(smallCol);
                if (checkChar == i) {
                    return false;
                }
            }
        }
        return true;
    }

}
