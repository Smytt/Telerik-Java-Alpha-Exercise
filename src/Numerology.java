import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Numerology {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String date = br.readLine();
        List<Integer> occurances = Stream.generate(() -> 0).limit(10).collect(Collectors.toList());

        findOcurances(date, occurances);
        occurances.forEach(number -> System.out.print(number + " "));
    }

    static void findOcurances(String date, List<Integer> occurances) {

        if (date.length() == 1) {
            int result = Integer.parseInt(date);
            occurances.set(result, occurances.get(result) + 1);
        }

        for (int i = 0; i < date.length() - 1; i++) {
            String before = date.substring(0, i);
            String after = "";
            if (i <= date.length() - 3) {
                after = date.substring(i + 2, date.length());
            }
            int a = Integer.parseInt(String.valueOf(date.charAt(i)));
            int b = Integer.parseInt(String.valueOf(date.charAt(i + 1)));
            int newNumber = (a + b) * (a ^ b) % 10;
            StringBuilder sb = new StringBuilder();
            sb.append(before).append(newNumber).append(after);
            String newString = new String(sb);
            findOcurances(newString, occurances);
        }
    }
}
