public class Labyrinth {
    public static void main(String[] args) {
        // 0 - can't step
        // 1 - passage
        // 2 - target
        // start from [startRow, startCol]
        int[][] labyrinth = {
                {1, 1, 1, 0, 2, 1, 1, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 1, 0, 0, 1},
                {0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                {1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                {0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
        };
        int startRow = 0;
        int startCol = 0;

        if (canReachTarget(labyrinth, startRow, startCol)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    static boolean canReachTarget(int[][] labyrinth, int currentRow, int currentCol) {
        if(currentCol < 0 || currentRow < 0 || currentRow >= labyrinth.length || currentCol >= labyrinth[0].length) {
            return false;
        }

        if (labyrinth[currentRow][currentCol] == 0) {
            return false;
        }

        if (labyrinth[currentRow][currentCol] == 2) {
            return true;
        }

        labyrinth[currentRow][currentCol] = 0;

        if (!canReachTarget(labyrinth, currentRow - 1, currentCol)) {
            if (!canReachTarget(labyrinth, currentRow, currentCol + 1)) {
                if (!canReachTarget(labyrinth, currentRow + 1, currentCol)) {
                    if (!canReachTarget(labyrinth, currentRow, currentCol - 1)) {
                        return false;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
