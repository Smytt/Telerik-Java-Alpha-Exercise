import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuickSort {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 7, 1, 2, 8, 4);

        quickSort(list, 0, list.size() - 1);
        System.out.println(list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" ")));
    }

    private static void quickSort(List<Integer> list, int left, int right) {
        if (left >= right) {
            return;
        }

        int index = partition(list, left, right);

        quickSort(list, index + 1, right);
        quickSort(list, left, index);
    }

    private static int partition(List<Integer> list, int start, int end) {
        int pivot = list.get(start);
        int index = start;

        for (int i = start + 1; i <= end; i++) {
            if (list.get(i) < pivot) {
                index++;
                if (i != index) {
                    int temp = list.get(index);
                    list.set(index, list.get(i));
                    list.set(i, temp);
                }
            }
        }

        if (index != start) {
            int temp = list.get(start);
            list.set(start, list.get(index));
            list.set(index, temp);
        }

        return index;
    }
}
