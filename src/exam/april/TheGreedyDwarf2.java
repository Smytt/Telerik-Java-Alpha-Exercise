package exam.april;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheGreedyDwarf2 {
    static void fakeInput() {
        String input = "3 3\n" +
                "10 10 0\n" +
                "10 10 10\n" +
                "10 10 10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String dimensions = br.readLine();
        int r = dimensions.charAt(0) - '0';
        int c = dimensions.charAt(2) - '0';
        int currentRow = 0;
        int currentCol = 0;
        int sum = 0;
        ArrayList<ArrayList<Integer>> field = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            String charRow = br.readLine();
            String[] chars = charRow.split(" ");
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < chars.length; j++) {
                int currentChar = Integer.parseInt(chars[j]);
                row.add(currentChar);
                if (currentChar == 0) {
                    currentRow = i;
                    currentCol = (j + 1) / 2;
                }
            }
            field.add(row);
        }



    }
}
