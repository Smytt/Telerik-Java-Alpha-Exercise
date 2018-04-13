package chess;

public class Queen extends Piece {
    public Queen(Color color, char col, int row) {
        super(color, col, row);
        type = Type.QUEEN;
    }

    @Override
    boolean isValidMovement(char col, int row) {
        System.out.println("Check if queen can move to " + col + row);
        return true;
    }
}
