package chess;

public class King extends Piece {
    public King(Color color, char col, int row) {
        super(color, col, row);
        type = Type.KING;
    }

    @Override
    boolean isValidMovement(char col, int row) {
        System.out.println("Check if king can move to " + col + row);
        return true;
    }
}
