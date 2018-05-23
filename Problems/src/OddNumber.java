import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class OddNumber {
    static void fakeInput() {
        String input = "13\n" +
                "-1\n" +
                "7\n" +
                "7\n" +
                "-9223372036854775808\n" +
                "7\n" +
                "-9223372036854775808\n" +
                "-3\n" +
                "7\n" +
                "0\n" +
                "-1\n" +
                "7\n" +
                "0\n" +
                "-3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Integer> occurances = new HashMap<>();

        int nums = Integer.parseInt(br.readLine());

        for (int i = 0; i < nums; i++) {
            String nextString = br.readLine();

            if (!occurances.containsKey(nextString)) {
                occurances.put(nextString, 0);
            }

            occurances.put(nextString, occurances.get(nextString) + 1);
        }

        for (String key: occurances.keySet()) {
            if (occurances.get(key)%2 == 1) {
                System.out.println(key);
                break;
            }
        }
//        occurances.keySet().forEach(key -> {
//            if (occurances.get(key) % 2 == 1) {
//                System.out.println(key);
//                return;
//            }
//        });
    }
}
