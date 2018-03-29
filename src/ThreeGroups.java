import java.util.Scanner;

public class ThreeGroups {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] numberStrings = s.nextLine().split(" ");
        int[] numbers = new int[numberStrings.length];

        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        int[] counts = {0, 0, 0};

        for (int x : numbers) {
            int remainder = x % 3;
            counts[remainder]++;
        }

        int[][] groups = {
                new int[counts[0]],
                new int[counts[1]],
                new int[counts[2]]
        };

        int zerosIndex = 0;
        int onesIndex = 0;
        int twosIndex = 0;

        int[] indices = {0, 0, 0};

        for (int x : numbers) {
            int remainder = x % 3;
            groups[remainder][indices[remainder]++] = x;
        }

        for (int[] group : groups) {
            if(group.length == 0) {
                continue;
            }
            for (int j = 0; j < group.length - 1; j++) {
                System.out.print(group[j] + " ");
            }
            System.out.println(group[group.length - 1]);
        }
    }
}
