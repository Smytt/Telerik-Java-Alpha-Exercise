package binary_heap_implementation;

public class Main {
    public static void main(String[] args) {
        MinBinaryHeap<Integer> heap = new MinBinaryHeap<>();
        heap.add(3);
        heap.add(31);
        heap.add(23);
        heap.add(1);
        heap.add(4);
        heap.add(55);
        heap.add(2);

        System.out.println(heap);

        System.out.println("extracted: " + heap.extractMin());
    }
}
