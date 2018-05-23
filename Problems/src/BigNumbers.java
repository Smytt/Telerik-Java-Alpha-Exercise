import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ArrayList<String> num1Seq = new ArrayList<>(Arrays.asList(br.readLine().split(" ")));
        ArrayList<String> num2Seq = new ArrayList<>(Arrays.asList(br.readLine().split(" ")));
        StringBuilder result = new StringBuilder();

        int maxSize = Math.max(num1Seq.size(), num2Seq.size());
        int carryOne = 0;

        for (int i = 0; i < maxSize; i++) {
            int num1 = i < num1Seq.size() ? Integer.parseInt(num1Seq.get(i)) : 0;
            int num2 = i < num2Seq.size() ? Integer.parseInt(num2Seq.get(i)) : 0;
            int newNumber = num1 + num2 + carryOne;
            carryOne = newNumber >= 10 ? 1 : 0;
            result.append(newNumber % 10);
            result.append(" ");
        }

        if (carryOne > 0) {
            result.append(1);
        }

        System.out.println(result);
    }
}
