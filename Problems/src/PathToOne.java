import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class PathToOne {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int original = n;
        int count = 0;
        Queue<Integer> q = new ArrayDeque<>();

        q.add(n);
        q.add(count);

        while (!q.isEmpty()) {
            n = q.poll();
            count = q.poll();

            if (n == 1) {
                break;
            }

            if (n > original + 1 || n <= 1) {
                continue;
            }

            count++;

            if (n % 2 == 0) {
                q.offer(n / 2);
                q.offer(count);
            } else {
                q.offer(n + 1);
                q.offer(count);
                q.offer(n - 1);
                q.offer(count);
            }
        }

        System.out.println(count);

    }
}
