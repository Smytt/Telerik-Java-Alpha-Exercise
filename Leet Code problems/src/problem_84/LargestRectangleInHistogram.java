package problem_84;

import java.util.Stack;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int result = new Solution().largestRectangleArea(new int[]{2, 1, 2});
        System.out.println(result);
    }
}

class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int max = 0;
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            if (s.isEmpty()) {
                s.push(i);
                continue;
            }

            int lastPushedIndex = s.peek();
            int lastNumber = heights[lastPushedIndex];
            int currentNumber = heights[i];
            if (currentNumber >= lastNumber) {
                s.push(i);
                continue;
            }

            while (lastNumber > currentNumber && !s.isEmpty()) {
                lastPushedIndex = s.pop();

                max = Math.max(max, lastNumber * (i - s.peek()));

                if (!s.isEmpty()) {

                    lastPushedIndex = s.peek();
                    lastNumber = heights[lastPushedIndex];
                }
            }

            s.push(i);
        }

        int lastPushedIndex = s.peek();
        int lastNumber = heights[lastPushedIndex];

        while (!s.isEmpty()) {
            lastPushedIndex = s.pop();

            max = Math.max(max, lastNumber * (heights.length - lastPushedIndex));

            if (!s.isEmpty()) {

                lastPushedIndex = s.peek();
                lastNumber = heights[lastPushedIndex];
            }
        }

        return max;
    }
}