package chess;

public class Piece {
    Color color;
    int row;
    char col;
    Type type;

    Piece() {

    }

    Piece(Color color, char col, int row) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    boolean isValidMovement(char col, int row) {
        return false;
    }

    @Override
    public String toString() {
        return "This is a " + color + " " + type + " and is located at " + col + row;
    }
}
