package tree_implementation;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {
    public static void main(String[] args) {
        Node<Integer> node = new Node<Integer>(4);
        node.addChild(5);
        node.addChild(7);
        node.getChildren().get(0).addChild(8);
        node.getChildren().get(0).addChild(9);
        node.getChildren().get(1).addChild(5);
        node.getChildren().get(1).addChild(15);

        //preOrder
        dfs(node);
    }

    private static void dfs(Node<Integer> node) {
        node.getChildren().forEach(child -> {
            dfs(child);
        });
        System.out.println(node);
    }
}
