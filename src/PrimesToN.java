import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrimesToN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        getPrimes(n).forEach(x -> System.out.print(x + " "));

    }

    static ArrayList<Integer> getPrimes(int n) {
        List<Boolean> isPrime = Stream.generate(() -> true)
                .limit(n + 1)
                .collect(Collectors.toList());
        isPrime.set(0, false);

        for (int number = 2; number < n + 1; number++) {
            for (int x = number * 2; x < n + 1; x += number) {
                isPrime.set(x, false);
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 0; i < isPrime.size(); i++) {
            if (isPrime.get(i)) {
                primes.add(i);
            }
        }

        return primes;
    }
}
