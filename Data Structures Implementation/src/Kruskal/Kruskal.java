package Kruskal;

import java.util.*;

public class Kruskal {
    private static final int GRAPH_SIZE = 6;
    private static List<int[]> graph = new ArrayList<>();

    static {
        graph.add(new int[]{0, 1, 4});
        graph.add(new int[]{0, 2, 2});
        graph.add(new int[]{1, 2, 3});
        graph.add(new int[]{1, 3, 5});
        graph.add(new int[]{2, 3, 1});
        graph.add(new int[]{3, 4, 9});
        graph.add(new int[]{3, 5, 7});
        graph.add(new int[]{4, 5, 1});
    }

    public List<int[]> kruskal() {
        List<int[]> result = new ArrayList<>();
        HashSet<Integer> usedNodes = new HashSet<>();
        boolean shouldTestForLoop = false;
        graph.sort(Comparator.comparingInt(x -> x[2]));

        for (int[] branch: graph) {
            int node1 = branch[0];
            int node2 = branch[1];

            if (usedNodes.contains(node1) && usedNodes.contains(node2)) {
                shouldTestForLoop = true;
            }

            usedNodes.add(branch[0]);
            usedNodes.add(branch[1]);
            result.add(branch);

            if(shouldTestForLoop) {
                if(hasLoop(node1, result)) {
                    revert(result, usedNodes);
                }
            }

            shouldTestForLoop = false;
        }

        return result;
    }

    private void revert(List<int[]> result, HashSet<Integer> usedNodes) {
        //todo revert
    }

    private boolean hasLoop(int node1, List<int[]> result) {
        int currentNode = node1;

        return true; //todo
    }
}
