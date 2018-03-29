import java.io.InputStreamReader;
import java.util.Scanner;

public class ReverseArray {

//    static void fakeInput() {
//        String input = "1 2 3 4 5 6 7";
//        System.setIn(new InputStreamReader(new InputS));
//    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] numbers = s.nextLine().split(" ");

        for (int i = numbers.length - 1; i >= 1; i--) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.print(numbers[0]);
    }
}
