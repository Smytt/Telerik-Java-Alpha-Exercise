import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AboveTheMainDiagonal {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int sum = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                sum += Math.pow(2, i+j);
            }
        }

        System.out.println(sum);
    }
}

