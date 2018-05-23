package tree_implementation;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private Node<T> parent = null;
    private List<Node<T>> children = null;
    private T value = null;

    public Node(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
    
    public void addChild(Node<T> child) {
        children.add(child);
    }

    public void addChild(T child) {
        Node<T> childNode = new Node<T>(child);
        childNode.setParent(this);
        children.add(childNode);
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
