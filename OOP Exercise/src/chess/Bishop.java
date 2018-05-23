package chess;

public class Bishop extends Piece {
    public Bishop(Color color, char col, int row) {
        super(color, col, row);
        type = Type.BISHOP;
    }

    @Override
    boolean isValidMovement(char col, int row) {
        System.out.println("Check if bishop can move to " + col + row);
        return true;
    }
}
