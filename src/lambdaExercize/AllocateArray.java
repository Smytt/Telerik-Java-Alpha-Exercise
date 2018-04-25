package lambdaExercize;

import java.util.Scanner;
import java.util.stream.IntStream;

public class AllocateArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        IntStream.range(0, sc.nextInt())
                .map(x -> x*5)
                .forEach(System.out::println);
    }
}
