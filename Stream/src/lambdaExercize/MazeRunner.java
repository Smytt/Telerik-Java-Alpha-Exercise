package lambdaExercize;

import java.util.Scanner;
import java.util.stream.Stream;

public class MazeRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Stream.generate(sc::nextLine)
                .limit(Integer.parseInt(sc.nextLine()))
                .forEach(MazeRunner::desideDirection);
    }

    private static void desideDirection(String s) {
        int evenSum = s.chars()
                .map(x -> x - '0')
                .filter(x -> x % 2 == 0)
                .sum();
        int oddSum = s.chars()
                .map(x -> x - '0')
                .filter(x -> x % 2 != 0)
                .sum();

        if(evenSum > oddSum) {
            System.out.println("left");
        }
        else if(oddSum> evenSum) {
            System.out.println("right");
        }
        else {
            System.out.println("straight");
        }
    }
}
