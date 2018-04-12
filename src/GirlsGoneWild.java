import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class GirlsGoneWild {

    static void fakeInput() {
        String input = "5\n" +
                "baca\n" +
                "3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int shirts = Integer.parseInt(br.readLine());
        char[] skirts = br.readLine().toCharArray();
        Arrays.sort(skirts);
        int girls = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> shirtsCombinations = new ArrayList<>();
        ArrayList<ArrayList<Character>> skirtsCombinations = new ArrayList<>();

        combineShirts(shirts, girls, 0, new ArrayList<>(), shirtsCombinations);
        System.out.println(shirtsCombinations);

    }

    private static void combineShirts(int max, int girls, int currentShirt, ArrayList<Integer> currentCombination, ArrayList<ArrayList<Integer>> shirtsCombinations) {
        if (currentCombination.size() == girls) {
            shirtsCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = currentShirt; i < max; i++) {
            currentCombination.add(i);
            combineShirts(max, girls, i + 1, currentCombination, shirtsCombinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
