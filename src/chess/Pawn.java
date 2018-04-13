package chess;

public class Pawn extends Piece {
    public Pawn(Color color, char col, int row) {
        super(color, col, row);
        type = Type.PAWN;
    }

    @Override
    boolean isValidMovement(char col, int row) {
        System.out.println("Check if paw can move to " + col + row);
        return true;
    }

}
