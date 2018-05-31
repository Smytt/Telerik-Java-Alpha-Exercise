package graph_implementation;

public class Main {
    public static void main(String[] args) {

        Graph<String> graph = graphInit();

//        dfs(graph);
//        bfs(graph);

    }

//    private static void dfs(Graph<String> graph) {
//        graph.forEach(vertex -> {
//            vertex
//        });
//    }

    private static Graph<String> graphInit() {
        Graph<String> graph = new Graph<>();
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");
        Node<String> nodeG = new Node<>("G");
        Node<String> nodeH = new Node<>("H");

        nodeA.addAdjacent(nodeB);
        nodeA.addAdjacent(nodeC);

        nodeB.addAdjacent(nodeA);
        nodeB.addAdjacent(nodeC);
        nodeB.addAdjacent(nodeE);

        nodeC.addAdjacent(nodeA);
        nodeC.addAdjacent(nodeB);
        nodeC.addAdjacent(nodeD);
        nodeC.addAdjacent(nodeE);

        nodeD.addAdjacent(nodeE);
        nodeD.addAdjacent(nodeC);

        nodeE.addAdjacent(nodeC);
        nodeE.addAdjacent(nodeF);

        nodeF.addAdjacent(nodeE);

        nodeH.addAdjacent(nodeG);

        nodeG.addAdjacent(nodeH);

        graph.add(nodeA);
        graph.add(nodeB);
        graph.add(nodeC);
        graph.add(nodeD);
        graph.add(nodeE);
        graph.add(nodeF);
        graph.add(nodeG);
        graph.add(nodeH);

        return graph;
    }
}
