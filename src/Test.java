import java.io.*;
import java.util.InputMismatchException;

public class Test {
    public static void main(String[] args) {
        int[] test = {1,2,3};
        test[0]++;
        test[0]++;
        test[0]++;
        test[0]++;

        System.out.println(test[0]);
    }
}