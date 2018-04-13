import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class Test {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> one = new ArrayList<>();
        one.add(3);
        one.add(5);
        ArrayList<Integer> two = new ArrayList<>();
        two.add(3);
        two.add(5);

        HashSet<ArrayList<Integer>> set = new HashSet<>();

        set.add(one);
        set.add(two);

        System.out.println(set);
    }
}
