package chess;

public class Knight extends Piece {
    public Knight(Color color, char col, int row) {
        super(color, col, row);
        type = Type.KNIGHT;
    }

    @Override
    boolean isValidMovement(char col, int row) {
        System.out.println("Check if knight can move to " + col + row);
        return true;
    }
}
