import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {
    public static void fakeInput() {
        String test = "3 3\n" +
                "10 10 0\n" +
                "10 10 10\n" +
                "10 10 10";

        System.setIn(new ByteArrayInputStream(test.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        //Scanner in = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        //int n = Integer.parseInt(st.nextToken());

        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        //in.nextLine();

        int[][] matrix = new int[rows][cols];

        int startRow = 0;
        int startCol = 0;

        for (int row = 0; row < matrix.length; row++) {

            String[] rowInput = br.readLine().split(" ");
            //String[] rowInput = in.nextLine().split(" ");

            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = Integer.parseInt(rowInput[col]);
                if (matrix[row][col] == 0) {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        //System.out.println(startRow + ", " + startCol);
        // move left, right, up, or down
        int[] dRows = {0, 0, -1, 1};
        int[] dCols = {-1, 1, 0, 0};

        int row = startRow;
        int col = startCol;

        int coins = 0;

        while (true) {

            int nextRowMax = 0;
            int nextColMax = 0;
            int maxValueNextRow = 0;

            // define next move (also if more than one neighboring cell with same value
            for (int dir = 0; dir < dRows.length; dir++) {
                int nextRow = row + dRows[dir];
                int nextCol = col + dCols[dir];

                // outside of the matrix
                if (!inRange(nextRow, rows) ||
                        !inRange(nextCol, cols)) {
                    continue;
                }

                if (matrix[nextRow][nextCol] > maxValueNextRow) {
                    maxValueNextRow = matrix[nextRow][nextCol];
                    nextRowMax = nextRow;
                    nextColMax = nextCol;
                }
            }


            // exit the matrix (loop) if all surrounding cells are zero
            // matrix[nextRowMax][nextColMax]
            if (maxValueNextRow == 0) {
                break;
            }

            coins++;
            matrix[nextRowMax][nextColMax]--;

            row = nextRowMax;
            col = nextColMax;
        }

        System.out.println(coins);
    }

    static boolean inRange(int value, int max) {
        return 0 <= value && value < max;
    }
}