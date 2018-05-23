package searching_algorithms_implemetation;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 7, 11, 134, 4445, 6678, 324324, 1324324, 1324324);

        int index = find(34, list);
        System.out.println(index);
    }

    private static int find(int i, List<Integer> list) {
        int start = 0;
        int end = list.size();
        return binarySearch(i, list, start, end);
    }

    private static int binarySearch(int i, List<Integer> list, int start, int end) {
        int midIndex = (end + start) / 2;

        if (list.get(midIndex) == i) {
            return midIndex;
        }

        if (start == end) {
            return -1;
        }

        if (list.get(midIndex) > i) {
            return binarySearch(i, list, start, midIndex);
        }

        return binarySearch(i, list, midIndex + 1, end);
    }
}
