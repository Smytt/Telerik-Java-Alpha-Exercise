package chess;

public class Main {
    public static void main(String[] args) {
        Pawn pawnB1 = new Pawn(Color.BLACK, 'A', 7);
        Pawn pawnB2 = new Pawn(Color.BLACK, 'B', 7);
        Pawn pawnB3 = new Pawn(Color.BLACK, 'C', 7);
        Pawn pawnB4 = new Pawn(Color.BLACK, 'D', 7);
        Pawn pawnB5 = new Pawn(Color.BLACK, 'E', 7);
        Pawn pawnB6 = new Pawn(Color.BLACK, 'F', 7);
        Pawn pawnB7 = new Pawn(Color.BLACK, 'G', 7);
        Pawn pawnB8 = new Pawn(Color.BLACK, 'H', 7);
        Rook rookB1 = new Rook(Color.BLACK, 'A', 8);
        Rook rookB2 = new Rook(Color.BLACK, 'H', 8);
        Knight knightB1 = new Knight(Color.BLACK, 'B', 8);
        Knight knightB2 = new Knight(Color.BLACK, 'G', 8);
        Bishop bishopB1 = new Bishop(Color.BLACK, 'C', 8);
        Bishop bishopB2 = new Bishop(Color.BLACK, 'F', 8);
        Queen kingB = new Queen(Color.BLACK, 'D', 8);
        King queenB = new King(Color.BLACK, 'E', 8);

        Pawn pawnW1 = new Pawn(Color.WHITE, 'A', 2);
        Pawn pawnW2 = new Pawn(Color.WHITE, 'B', 2);
        Pawn pawnW3 = new Pawn(Color.WHITE, 'C', 2);
        Pawn pawnW4 = new Pawn(Color.WHITE, 'D', 2);
        Pawn pawnW5 = new Pawn(Color.WHITE, 'E', 2);
        Pawn pawnW6 = new Pawn(Color.WHITE, 'F', 2);
        Pawn pawnW7 = new Pawn(Color.WHITE, 'G', 2);
        Pawn pawnW8 = new Pawn(Color.WHITE, 'H', 2);
        Rook rookW1 = new Rook(Color.WHITE, 'A', 1);
        Rook rookW2 = new Rook(Color.WHITE, 'H', 1);
        Knight knightW1 = new Knight(Color.WHITE, 'B', 1);
        Knight knightW2 = new Knight(Color.WHITE, 'G', 1);
        Bishop bishopW1 = new Bishop(Color.WHITE, 'C', 1);
        Bishop bishopW2 = new Bishop(Color.WHITE, 'F', 1);
        Queen kingW = new Queen(Color.WHITE, 'D', 1);
        King queenW = new King(Color.WHITE, 'E', 1);

        pawnW1.isValidMovement('B', 2);
        knightB2.isValidMovement('H', 5);
        bishopW1.isValidMovement('C', 7);
        System.out.println(pawnW1);
        System.out.println(queenB);

    }
}
