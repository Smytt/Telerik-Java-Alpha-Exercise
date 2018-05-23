import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AboveTheMainDiagonal2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum += (long)Math.pow(2, i+j);
            }
        }

        System.out.println(sum);
    }
}

