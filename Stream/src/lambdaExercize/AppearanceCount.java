package lambdaExercize;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppearanceCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Integer> numbers = IntStream
                .generate(sc::nextInt)
                .limit(n)
                .boxed()
                .collect(Collectors.toList());

        int k = sc.nextInt();

        long found = numbers
                .stream()
                .filter(x -> x == k)
                .count();

        System.out.println(found);
    }
}
