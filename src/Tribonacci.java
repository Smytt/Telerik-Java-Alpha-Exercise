import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Tribonacci {
    static void fakeInput() {
        String input = "2\n" +
                "3\n" +
                "4\n" +
                "10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger first = BigInteger.valueOf(Integer.parseInt(br.readLine()));
        BigInteger second = BigInteger.valueOf(Integer.parseInt(br.readLine()));
        BigInteger third = BigInteger.valueOf(Integer.parseInt(br.readLine()));
        int target = Integer.parseInt(br.readLine());

        System.out.println(findTribonacciElement(first, second, third, target, 3));
    }

    private static BigInteger findTribonacciElement(BigInteger first, BigInteger second, BigInteger third, int target, int count) {
        if (count == target) {
            return third;
        }

        return findTribonacciElement(second, third, first.add(second.add(third)), target, count + 1);
    }
}
