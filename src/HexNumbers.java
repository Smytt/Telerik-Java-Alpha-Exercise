import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HexNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        int longestSequence = 1;
        int occurencesOfSequence = 1;
        int currentSequence = 1;

        for (int i = 0; i < n; i++) {
            String hex = Integer.toHexString(Integer.parseInt(br.readLine()));
            result.append(hex);
        }

        for (int i = 1; i < result.length(); i++) {
            if (result.charAt(i) == result.charAt(i - 1)) {
                currentSequence++;
                if (currentSequence == longestSequence) {
                    occurencesOfSequence++;
                } else if (currentSequence > longestSequence) {
                    longestSequence = currentSequence;
                    occurencesOfSequence = 1;
                }
            } else {
                if (longestSequence == 1) {
                    occurencesOfSequence++;
                } else {
                    currentSequence = 1;
                }

            }
        }

        System.out.println(longestSequence + " " + occurencesOfSequence);
    }
}
