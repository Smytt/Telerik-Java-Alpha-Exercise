package retakeExam;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class JoroTheNaughty {

    static void fakeInput() {
        String input =
                "6 7 3\n" +
                        "0 0\n" +
                        "2 2\n" +
                        "-2 2\n" +
                        "3 -1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) {
        fakeInput();

        Scanner sc = new Scanner(System.in);

        String[] command = sc.nextLine().split(" ");
        String[] start = sc.nextLine().split(" ");

        int n = Integer.parseInt(command[0]);
        int m = Integer.parseInt(command[1]);
        int jumps = Integer.parseInt(command[2]);
        boolean[][] hasStepped = new boolean[n][m];

        int r = Integer.parseInt(start[0]);
        int c = Integer.parseInt(start[1]);
        int[] rowJumps = new int[jumps];
        int[] colJumps = new int[jumps];
        for (int i = 0; i < jumps; i++) {
            String[] nextCell = sc.nextLine().split(" ");
            int moveRow = Integer.parseInt(nextCell[0]);
            int moveCol = Integer.parseInt(nextCell[1]);
            rowJumps[i] = moveRow;
            colJumps[i] = moveCol;
        }
        long sum = 0;
        int jumpIndex = 0;
        int totalJumps = 0;

        while (true) {
            totalJumps++;
            int moveRow = rowJumps[jumpIndex];
            int moveCol = colJumps[jumpIndex];
            sum += r * m + c + 1;

            if (willEscape(n, m, r, c, moveRow, moveCol)) {
                System.out.printf("escaped " + sum);
                break;
            }

            hasStepped[r][c] = true;

            r += moveRow;
            c += moveCol;

            if (hasStepped[r][c]) {
                System.out.println("caught " + totalJumps);
                break;
            }

            jumpIndex++;
            jumpIndex = jumpIndex % jumps;
        }
    }

    private static boolean willEscape(int n, int m, int r, int c, int moveRow, int moveCol) {
        if (r + moveRow >= n || r + moveRow < 0) {
            return true;
        }
        if (c + moveCol >= m || c + moveCol < 0) {
            return true;
        }
        return false;
    }
}
