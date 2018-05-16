import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) throws IOException {
        List<Integer> test = Arrays.asList(1,2,3,4,5);
        test(test);
        print(test);
    }

    private static void print(List<Integer> test) {
        for (int n: test) {
            System.out.println(n);
        }
    }

    private static void test(List<Integer> test) {
        test.set(1,100);
    }
}