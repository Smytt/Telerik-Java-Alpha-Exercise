package lambdaExercize;

import java.util.Scanner;
import java.util.stream.Collectors;

public class SecretMessage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder(
        sc.nextLine()
                .chars()
                .filter(Character::isLetter)
                .mapToObj(x -> (char) x)
                .map(x -> x.toString())
                .collect(Collectors.joining(""))).reverse();

        System.out.println(sb);
    }
}
