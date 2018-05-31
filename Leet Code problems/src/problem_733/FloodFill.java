package problem_733;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FloodFill {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1},
        };
        int sr = 1, sc = 1, newColor = 2;
        image = s.floodFill(image, sr, sc, newColor);

        Arrays.stream(image).forEach(x -> {
            Arrays.stream(x).forEach(y -> System.out.print(y + " "));
            System.out.println();
        });
    }

    static class Solution {

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            int currentColor = image[sr][sc];
            int codeFactor = image[0].length;
            Queue<Integer> points = new ArrayDeque<>();

            if (currentColor == newColor) {
                return image;
            }

            points.offer(code(sr, sc, codeFactor));

            while (!points.isEmpty()) {
                int address = points.poll();
                int r = address / codeFactor;
                int c = address % codeFactor;

                image[r][c] = newColor;

                for (int[] direction : directions) {
                    if (isValid(image, r, c, direction[0], direction[1], currentColor)) {
                        points.offer(code(r + direction[0], c + direction[1], image[0].length));
                    }
                }
            }
            return image;
        }

        private Integer code(int sr, int sc, int length) {
            return sr * length + sc;
        }

        private boolean isValid(int[][] image, int sr, int sc, int directionR, int directionC, int currentColor) {
            int r = sr + directionR;
            int c = sc + directionC;
            return r >= 0 && r < image.length && c >= 0 && c < image[r].length && image[r][c] == currentColor;
        }
    }
}
