package graph_implementation;

import javafx.scene.chart.ValueAxis;

import java.util.ArrayList;

public class Node<T> extends ArrayList<Node<T>> {
    private T value;

    public Node (T value) {
        this.value = value;
    }

    public void addAdjacent(Node<T> node) {
        this.add(node);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
