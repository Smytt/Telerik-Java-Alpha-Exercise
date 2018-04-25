import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) throws IOException {
        int[] test = {1, 2, 3, 4, 5};

        for (int i = 0; i < test.length; i++) {
            test[i]--;
        }

        for (int num: test) {
            System.out.println(num);
        }
    }
}