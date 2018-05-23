import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class GirlsGoneWild {

    static void fakeInput() {
        String input = "6\n" +
                "fthavy\n" +
                "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int shirts = Integer.parseInt(br.readLine());
        char[] skirts = br.readLine().toCharArray();
        int girls = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> shirtsCombinations = new ArrayList<>();
        Skirts skirtsCombinations = new Skirts();

        combineShirts(shirts, girls, 0, new ArrayList<>(), shirtsCombinations);
        combineSkirts(skirts, girls, new HashSet<>(), -1, new ArrayList<>(), skirtsCombinations);

        ArrayList<String> output = new ArrayList<>();
        System.out.println(shirtsCombinations.size() * skirtsCombinations.skirtsCombinations.size());

        for (ArrayList<Integer> oneShirtsCombination: shirtsCombinations){
            for (ArrayList<Character> oneSkirtCombination: skirtsCombinations.skirtsCombinations) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i< girls; i++) {
                    sb
                            .append(oneShirtsCombination.get(i))
                            .append(oneSkirtCombination.get(i));
                    if (i != girls - 1) {
                        sb.append('-');
                    }
                }
                output.add(sb.toString());
            }
        }

        Collections.sort(output);

        output.forEach(System.out::println);
    }

    private static void combineSkirts(char[] skirts, int girls, HashSet<Integer> usedIndexes, int currentSkirtIndex, ArrayList<Character> currentCombination, Skirts skirtsCombinations) {
        if (currentCombination.size() == girls) {
            if(!skirtsCombinations.uniqueSkirtsCombinations.contains(currentCombination)) {
                skirtsCombinations.skirtsCombinations.add(new ArrayList<>(currentCombination));
                skirtsCombinations.uniqueSkirtsCombinations.add(new ArrayList<>(currentCombination));
            }
            return;
        }
        for (int i = 0; i < skirts.length; i++) {
            if(usedIndexes.contains(i)) {
                continue;
            }
            usedIndexes.add(i);
            char currentSkirt = skirts[i];
            currentCombination.add(currentSkirt);
            combineSkirts(skirts, girls, usedIndexes, i, currentCombination, skirtsCombinations);
            usedIndexes.remove(i);
            currentCombination.remove(currentCombination.size() - 1);
        }
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

class Skirts {
    ArrayList<ArrayList<Character>> skirtsCombinations = new ArrayList<>();
    HashSet<ArrayList<Character>> uniqueSkirtsCombinations = new HashSet<>();
}
