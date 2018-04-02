import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class SubsetOfSumS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());
        String[] numbersAsStrings = br.readLine().split(" ");
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String number : numbersAsStrings) {
            int currentNumber = Integer.parseInt(number);
            if (currentNumber <= s) {
                numbers.add(currentNumber);
            }
        }

        int n = numbers.size();
        boolean hasSum = isSubsetSum(numbers, n, s);


        System.out.println(hasSum ? "yes" : "no");
    }

    private static boolean isSubsetSum(ArrayList<Integer> set, int n, int sum)
    {
        // The value of subset[i][j] will be true if there
        // is a subset of set[0..j-1] with sum equal to i
        boolean[][] subset = new boolean[sum+1][n+1];

        // If sum is 0, then answer is true
        for (int i = 0; i <= n; i++)
            subset[0][i] = true;

        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= sum; i++)
            subset[i][0] = false;

        // Fill the subset table in botton up manner
        for (int i = 1; i <= sum; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                subset[i][j] = subset[i][j-1];
                if (i >= set.get(j-1))
                    subset[i][j] = subset[i][j] ||
                            subset[i - set.get(j-1)][j-1];
            }
        }

        return subset[sum][n];
    }
}
