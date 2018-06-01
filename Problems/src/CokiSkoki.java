import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CokiSkoki {
    static void fakeInput() {
        String input = "6\n" +
                "1 4 2 6 3 4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfBuildings = Integer.parseInt(br.readLine());
        String[] strNums = br.readLine().split(" ");

        int maxJumps = 0;
        int[] buildings = new int[numberOfBuildings];
        int[] nextBuildingIndex = new int[numberOfBuildings];
        int[] totalJumps = new int[numberOfBuildings];
        for (int i = 0; i < buildings.length; i++) {
            buildings[i] = Integer.parseInt(strNums[i]);
        }

        nextBuildingIndex[buildings.length - 1] = 0;
        totalJumps[buildings.length - 1] = 0;

        for (int i = buildings.length - 2; i > -1; i--) {

            for (int j = i + 1; j < buildings.length; j++) {
                if (buildings[j] > buildings[i]) {
                    nextBuildingIndex[i] = j;
                    break;
                }
            }

            if (nextBuildingIndex[i] > 0) {
                totalJumps[i] += totalJumps[nextBuildingIndex[i]] + 1;
            }
        }
        System.out.println(Arrays.stream(totalJumps).max().getAsInt());
        System.out.println(Arrays.stream(totalJumps).boxed().map(Objects::toString).collect(Collectors.joining(" ")));
    }
}