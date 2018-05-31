package problem_735;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class AsteroidCollision {
    public static void main(String[] args) {
        int[] result = new Solution().asteroidCollision(new int[]{5, 10, -5});

        Arrays.stream(result).forEach(System.out::println);
    }
}

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> items = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            boolean bothExplode = false;
            int newAsteroid = asteroids[i];

            if (items.size() == 0 || newAsteroid > 0) {
                items.push(newAsteroid);
                continue;
            }

            if (items.peek() * newAsteroid > 0) {
                items.push(newAsteroid);
                continue;
            }

            while (items.size() != 0 && newAsteroid < 0 && items.peek() > 0) {
                int lastAsteroid = items.pop();
                if (Math.abs(lastAsteroid) == Math.abs(newAsteroid)) {
                    bothExplode = true;
                    break;
                }
                if (Math.abs(lastAsteroid) > Math.abs(newAsteroid)) {
                    newAsteroid = lastAsteroid;
                }
            }

            if (bothExplode) continue;

            items.push(newAsteroid);
        }

        int[] result = new int[items.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = items.get(i);
        }

        return result;
    }
}