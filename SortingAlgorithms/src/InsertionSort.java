import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InsertionSort {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3,7,1,2,8,4);

        insertionSort(list);
        System.out.println(list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" ")));
    }

    private static void insertionSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            while (j > 0 && list.get(j) < list.get(j - 1)) {
                int temp = list.get(j);
                list.set(j, list.get(j-1));
                list.set(j-1, temp);
                j--;
            }
        }
    }
}
