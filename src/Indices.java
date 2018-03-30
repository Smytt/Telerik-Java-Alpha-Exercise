import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Indices {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] numberStrings = br.readLine().split(" ");
        ArrayList<Integer> numbers = new ArrayList<>(n);
        ArrayList<Integer> sequence = new ArrayList<>(n);
        HashSet<Integer> occurances = new HashSet<>();
        ArrayList<Integer> looping;
        ArrayList<Integer> nonLooping;

        for (String str : numberStrings) {
            numbers.add(Integer.parseInt(str));
        }

        int currentIndex = 0;
        boolean isLooping = false;
        int loopStart = 0;

        while (true) {
            int nextIndex = numbers.get(currentIndex);
            sequence.add(currentIndex);
            occurances.add(currentIndex);

            if (occurances.contains(nextIndex)) {
                isLooping = true;
                loopStart = sequence.indexOf(nextIndex);
                break;
            }

            if (nextIndex >= numbers.size() || nextIndex < 0) {
                break;
            }
            currentIndex = nextIndex;
        }

        if (isLooping) {
            looping = new ArrayList<>(sequence.subList(loopStart, sequence.size()));
            if (looping.size() < sequence.size()) {
                nonLooping = new ArrayList<>(sequence.subList(0, loopStart));
                for (int i = 0; i < nonLooping.size() - 1; i++) {
                    System.out.print(nonLooping.get(i) + " ");
                }
                System.out.print(nonLooping.get(nonLooping.size() - 1));
            }
            System.out.print("(");
            for (int i = 0; i < looping.size() - 1; i++) {
                System.out.print(looping.get(i) + " ");
            }
            System.out.print(looping.get(looping.size() - 1) + ")");
        } else {
            for (int i = 0; i < sequence.size() - 1; i++) {
                System.out.print(sequence.get(i) + " ");
            }
            System.out.print(sequence.get(sequence.size() - 1));
        }

    }
}
