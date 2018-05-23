import java.io.*;
import java.util.*;

public class Crossword {

    static boolean done = false;

    static void fakeInput() {
        String input =
                "----------\n" +
                        "-++++++++-\n" +
                        "-++++++++-\n" +
                        "-++++++++-\n" +
                        "-++++++++-\n" +
                        "-++++++++-\n" +
                        "-++++++++-\n" +
                        "-++++++++-\n" +
                        "-++++++++-\n" +
                        "----------\n" +
                        "SCDEFGHIJB;AKLMNOPQRS;JTUVWXYZAB;ABCDEFGHIJ";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }


    static char[][] crosswordPuzzle(char[][] crossword, String[] hints) {

        done = true;

        for (int row = 0; row < crossword.length; row++) {
            for (int col = 0; col < crossword[row].length; col++) {
                char cell = crossword[row][col];

                if (cell != '-') {
                    continue;
                }

                done = false;

                //check if found '-' is part of already started word A---- -> both vertically and horizontally
                if (isPartOfHorizontalWord(crossword, row, col)) {
                    StringBuilder wordTemplate = findWordTemplate(crossword, row, col - 1, true);
                    ArrayList<String> possibleWords = findPossibleWords(wordTemplate, hints);
                    for (String possibleWord : possibleWords) {
                        applyPossibleWord(crossword, row, col, possibleWord, true);
                        removeFromHints(possibleWord, hints);
                        crossword = crosswordPuzzle(crossword, hints);
                        if (done) {
                            return crossword;
                        }
                        revertHint(hints, possibleWord);
                        revertWord(crossword, row, col, wordTemplate, true);
                    }
                }
                if (isPartOfVerticalWord(crossword, row, col)) {
                    StringBuilder wordTemplate = findWordTemplate(crossword, row - 1, col, false);
                    ArrayList<String> possibleWords = findPossibleWords(wordTemplate, hints);
                    for (String possibleWord : possibleWords) {
                        applyPossibleWord(crossword, row, col, possibleWord, false);
                        removeFromHints(possibleWord, hints);
                        crossword = crosswordPuzzle(crossword, hints);
                        if (done) {
                            return crossword;
                        }
                        revertHint(hints, possibleWord);
                        revertWord(crossword, row, col, wordTemplate, false);
                    }
                }

            }
        }
        return crossword;

    }

    private static void revertHint(String[] hints, String possibleWord) {
        for (int i = 0; i < hints.length; i++) {
            if (hints[i].length() == 0) {
                hints[i] = possibleWord;
                break;
            }
        }
    }

    private static void removeFromHints(String possibleWord, String[] hints) {
        for (int i = 0; i < hints.length; i++) {
            if (possibleWord.equals(hints[i])) {
                hints[i] = "";
            }
        }
    }

    private static void revertWord(char[][] crossword, int row, int col, StringBuilder wordTemplate, boolean horizontal) {
        if (horizontal) {
            if (col > 0 && crossword[row][col - 1] != '+') {
                col--;
            }
            for (int i = 0; i < wordTemplate.length(); i++) {
                crossword[row][col + i] = wordTemplate.charAt(i);
            }
        } else {
            if (row > 0 && crossword[row - 1][col] != '+') {
                row--;
            }
            for (int i = 0; i < wordTemplate.length(); i++) {
                crossword[row + i][col] = wordTemplate.charAt(i);
            }
        }
    }

    private static void applyPossibleWord(char[][] crossword, int row, int col, String possibleWord, boolean horizontal) {
        if (horizontal) {
            if (col > 0 && crossword[row][col - 1] != '+') {
                col--;
            }
            for (int i = 0; i < possibleWord.length(); i++) {
                crossword[row][col + i] = possibleWord.charAt(i);
            }
        } else {
            if (row > 0 && crossword[row - 1][col] != '+') {
                row--;
            }
            for (int i = 0; i < possibleWord.length(); i++) {
                crossword[row + i][col] = possibleWord.charAt(i);
            }
        }
    }

    private static ArrayList<String> findPossibleWords(StringBuilder wordTemplate, String[] hints) {
        ArrayList<String> possibleWords = new ArrayList<>();
        for (int i = 0; i < hints.length; i++) {
            boolean found = true;
            String hint = hints[i];
            if (hint.length() != wordTemplate.length()) {
                continue;
            }
            for (int c = 0; c < wordTemplate.length(); c++) {
                if (wordTemplate.charAt(c) == '-') {
                    continue;
                }
                if (wordTemplate.charAt(c) != hint.charAt(c)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                possibleWords.add(hint);
            }
        }

        return possibleWords;
    }

    private static StringBuilder findWordTemplate(char[][] crossword, int row, int col, boolean horizontal) {
        StringBuilder wordTemplate = new StringBuilder();
        if (horizontal) {
            for (int i = col; i < crossword[row].length; i++) {
                if (i < 0 || crossword[row][i] == '+') {
                    if (i != col) break;
                } else {
                    wordTemplate.append(crossword[row][i]);
                }
            }
        } else {
            for (int i = row; i < crossword.length; i++) {
                if (i < 0 || crossword[i][col] == '+') {
                    if (i != row) break;
                } else {
                    wordTemplate.append(crossword[i][col]);
                }
            }
        }
        return wordTemplate;
    }

    private static boolean isPartOfVerticalWord(char[][] crossword, int row, int col) {
        if (row != 0 && (crossword[row - 1][col] == '-' || Character.isLetter(crossword[row - 1][col]))) return true;
        if (row != crossword.length - 1 && (crossword[row + 1][col] == '-' || Character.isLetter(crossword[row + 1][col])))
            return true;
        return false;
    }

    private static boolean isPartOfHorizontalWord(char[][] crossword, int row, int col) {
        if (col != 0 && (crossword[row][col - 1] == '-' || Character.isLetter(crossword[row][col - 1]))) return true;
        if (col != crossword[row].length - 1 && (crossword[row][col + 1] == '-' || Character.isLetter(crossword[row][col + 1])))
            return true;
        return false;
    }

    public static void main(String[] args) {
        fakeInput();
        Scanner in = new Scanner(System.in);
        char[][] crossword = new char[10][10];
        for (int crossword_i = 0; crossword_i < 10; crossword_i++) {
            crossword[crossword_i] = in.next().toCharArray();
        }
        String[] hints = in.next().split(";");

        char[][] result = crosswordPuzzle(crossword, hints);

        for (char[] row : result) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }

        in.close();
    }
}
