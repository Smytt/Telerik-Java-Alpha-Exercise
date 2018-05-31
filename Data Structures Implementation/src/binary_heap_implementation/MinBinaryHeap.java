package binary_heap_implementation;

import java.util.ArrayList;

public class MinBinaryHeap<T extends Comparable> {
    private ArrayList<T> heap;

    public MinBinaryHeap() {
        heap = new ArrayList<>();
    }

    public void add(T element) {
        heap.add(element);
        heapifyUp();
    }

    public T extractMin() {
        T root = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);

        heapify(0);

        return root;
    }

    private void heapify(int i) {
        System.out.println(heap);
        T left, right;
        if (getLeft(i) >= heap.size()) {
            left = null;
        }
        else {
            left = heap.get(getLeft(i));

        }
        if (getRight(i) >= heap.size()) {
            right = null;
        }
        else {
            right = heap.get(getRight(i));
        }

        if (left != null) {
            if (right != null) {
                if (left.compareTo(right) <= 0) {
                    if (left.compareTo(heap.get(i)) < 0) {
                        swap(i, getLeft(i));
                        heapify(getLeft(i));
                    }
                }
            }
            else if (left.compareTo(heap.get(i)) < 0) {
                swap(i, getLeft(i));
                heapify(getLeft(i));
            }
        }
        if (right != null) {
            if (left != null) {
                if (right.compareTo(left) < 0) {
                    if (right.compareTo(heap.get(i)) < 0) {
                        swap(i, getRight(i));
                        heapify(getRight(i));
                    }
                }
            }
            else if (right.compareTo(heap.get(i)) < 0) {
                swap(i, getRight(i));
                heapify(getRight(i));
            }
        }
    }

    private void heapifyUp() {
        int childIndex = heap.size() - 1;
        int parentIndex = getParent(childIndex);

        while (heap.get(childIndex).compareTo(heap.get(parentIndex)) < 0) {
            swap(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = getParent(childIndex);
        }
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    private int getParent(int child) {
        return (child - 1) / 2;
    }

    private int getLeft(int parent) {
        return parent * 2 + 1;
    }

    private int getRight(int parent) {
        return parent * 2 + 2;
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
