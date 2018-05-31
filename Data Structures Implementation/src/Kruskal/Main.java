package Kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       List<int[]> tree = new Kruskal().kruskal();

       tree.forEach(branch -> {
           Arrays.stream(branch).forEach( x -> System.out.print(x + " "));
           System.out.println();
       });
    }
}
