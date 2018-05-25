import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RadixSort {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 17, 145, 212, 80, 4);

        sort(list);
        System.out.println(list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" ")));

    }

    private static int findMax(List<Integer> list) {
        int max = list.get(0);

        for (int n : list) {
            if (n > max) {
                max = n;
            }
        }

        return max;
    }

    private static void sort(List<Integer> list) {
        List<Integer> tempList = Stream
                .generate(() -> 0)
                .limit(list.size())
                .collect(Collectors.toList());

        int maxNumber = findMax(list);
        for (int i = 1; maxNumber / i > 0; i *= 10) {
            radixSort(list, i, tempList);
        }
    }

    private static void radixSort(List<Integer> list, int base, List<Integer> tempList) {
        List<Integer> buckets = Stream
                .generate(() -> 0)
                .limit(10)
                .collect(Collectors.toList());

        for (int number : list) {
            int index = (number / base) % 10;
            buckets.set(index, buckets.get(index) + 1);
        }

        for (int i = 0; i < buckets.size() - 1; i++) {
            buckets.set(i + 1, buckets.get(i) + buckets.get(i + 1));
        }

        for (int i = list.size() - 1; i > -1; i--) {
            int bucketIndex = (list.get(i) / base) % 10;
            tempList.set(buckets.get(bucketIndex) - 1, list.get(i));
            buckets.set(bucketIndex, buckets.get(bucketIndex) - 1);
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, tempList.get(i));
        }
    }

}
