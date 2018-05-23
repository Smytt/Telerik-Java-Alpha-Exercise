package lambdaExercize;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class MaxSumOfKElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int sum = Stream
                .generate(sc::nextInt)
                .limit(n)
                .sorted(Comparator.reverseOrder())
                .limit(k)
                .mapToInt(x -> x)
                .sum();

        System.out.println(sum);

    }
}
