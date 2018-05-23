package chess;

public class Rook extends Piece {
    public Rook(Color color, char col, int row) {
        super(color, col, row);
        type = Type.ROOK;
    }

    @Override
    boolean isValidMovement(char col, int row) {
        System.out.println("Check if rook can move to " + col + row);
        return true;
    }
}
