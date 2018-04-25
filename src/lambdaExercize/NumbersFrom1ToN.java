package lambdaExercize;

import java.util.Scanner;
import java.util.stream.IntStream;

public class NumbersFrom1ToN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        IntStream.range(1, sc.nextInt() + 1)
                .mapToObj(x -> String.valueOf(x) + " ")
                .forEach(x -> System.out.print(x + " "));

    }
}
