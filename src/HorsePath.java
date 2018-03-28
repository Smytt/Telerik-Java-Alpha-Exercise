import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HorsePath {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        int[][] map = new int[size][size];
        int currentStep = 1;

        int[] cp = {0, 0};

        while (true) {
            map[cp[0]][cp[1]] = currentStep++;
            cp = findNextStep(cp, map);
            if (map[cp[0]][cp[1]] != 0) break;
        }

        for (int[] i : map) {
            System.out.println(Arrays.stream(i)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ")));
        }
    }

    private static int[] findNextStep(int[] cp, int[][] map) {
        if (cp[0] >= 2 && cp[1] >= 1 &&                                     map[cp[0] - 2][cp[1] - 1] == 0) return new int[]{cp[0] - 2, cp[1] - 1};
        if (cp[0] >= 2 && cp[1] <= map.length - 1 - 1 &&                    map[cp[0] - 2][cp[1] + 1] == 0) return new int[]{cp[0] - 2, cp[1] + 1};
        if (cp[0] >= 1 && cp[1] >= 2 &&                                     map[cp[0] - 1][cp[1] - 2] == 0) return new int[]{cp[0] - 1, cp[1] - 2};
        if (cp[0] >= 1 && cp[1] <= map.length - 1 - 2 &&                    map[cp[0] - 1][cp[1] + 2] == 0) return new int[]{cp[0] - 1, cp[1] + 2};
        if (cp[0] <= map.length - 1 - 1 && cp[1] >= 2 &&                    map[cp[0] + 1][cp[1] - 2] == 0) return new int[]{cp[0] + 1, cp[1] - 2};
        if (cp[0] <= map.length - 1 - 1 && cp[1] <= map.length - 1 - 2 &&   map[cp[0] + 1][cp[1] + 2] == 0) return new int[]{cp[0] + 1, cp[1] + 2};
        if (cp[0] <= map.length - 1 - 2 && cp[1] >= 1 &&                    map[cp[0] + 2][cp[1] - 1] == 0) return new int[]{cp[0] + 2, cp[1] - 1};
        if (cp[0] <= map.length - 1 - 2 && cp[1] <= map.length - 1 - 1 &&   map[cp[0] + 2][cp[1] + 1] == 0) return new int[]{cp[0] + 2, cp[1] + 1};

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return cp;
    }
}
