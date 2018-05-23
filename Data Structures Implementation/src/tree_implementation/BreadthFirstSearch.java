package tree_implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        Node<Integer> node = new Node<Integer>(4);
        node.addChild(5);
        node.addChild(7);
        node.getChildren().get(0).addChild(8);
        node.getChildren().get(0).addChild(9);
        node.getChildren().get(1).addChild(5);
        node.getChildren().get(1).addChild(15);

        bfs(node);
    }

    private static void bfs(Node<Integer> node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (queue.size() != 0) {
            node = queue.poll();
            node.getChildren().forEach(queue::offer);
            System.out.println(node);
        }
    }
}
