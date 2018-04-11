import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class GirlsGoneWild {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int shirts = Integer.parseInt(br.readLine());
        char[] skirts = br.readLine().toCharArray();
        Arrays.sort(skirts);
        int girls = Integer.parseInt(br.readLine());
    }
}
