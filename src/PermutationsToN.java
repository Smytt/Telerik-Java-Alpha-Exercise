import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PermutationsToN {
    public static void main(String[] args) {
        int n = 5;
        List<Integer> permutation = Stream.generate(() -> 4).limit(n).collect(Collectors.toList());
        getAllPermutations(0, n, permutation, new HashSet<>());
    }

    static void getAllPermutations(int index, int n, List<Integer> permutation, HashSet<Integer> used) {
        if (index == n) {
            System.out.println(permutation);
            return;
        }

        for (int value = 1; value < n + 1; value++) {
            if (used.contains(value)) {
                continue;
            }

            used.add(value);
            permutation.set(index, value);
            getAllPermutations(index + 1, n, permutation, used);
            used.remove(value);
        }
    }
}
