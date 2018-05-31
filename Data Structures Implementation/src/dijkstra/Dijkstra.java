package dijkstra;

import java.util.HashMap;
import java.util.stream.Stream;

public class Dijkstra {
    private static final int GRAPH_SIZE = 6;
    private static HashMap<String, Integer> nodes;
    private static int[][] graph = new int[GRAPH_SIZE][GRAPH_SIZE];

    static {
        nodes = new HashMap<>();
        nodes.put("A", 0);
        nodes.put("B", 1);
        nodes.put("C", 2);
        nodes.put("D", 3);
        nodes.put("E", 4);
        nodes.put("F", 5);

        for (int i = 0; i < GRAPH_SIZE; i++) {
            for (int j = 0; j < GRAPH_SIZE; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        graph[nodes.get("A")][nodes.get("A")] = 0;
        graph[nodes.get("A")][nodes.get("B")] = 4;
        graph[nodes.get("A")][nodes.get("C")] = 2;

        graph[nodes.get("B")][nodes.get("B")] = 0;
        graph[nodes.get("B")][nodes.get("A")] = 4;
        graph[nodes.get("B")][nodes.get("C")] = 3;
        graph[nodes.get("B")][nodes.get("D")] = 5;

        graph[nodes.get("C")][nodes.get("C")] = 0;
        graph[nodes.get("C")][nodes.get("A")] = 2;
        graph[nodes.get("C")][nodes.get("B")] = 3;
        graph[nodes.get("C")][nodes.get("D")] = 1;

        graph[nodes.get("D")][nodes.get("D")] = 0;
        graph[nodes.get("D")][nodes.get("B")] = 5;
        graph[nodes.get("D")][nodes.get("C")] = 1;
        graph[nodes.get("D")][nodes.get("E")] = 9;
        graph[nodes.get("D")][nodes.get("F")] = 7;

        graph[nodes.get("E")][nodes.get("E")] = 0;
        graph[nodes.get("E")][nodes.get("D")] = 9;
        graph[nodes.get("E")][nodes.get("F")] = 1;

        graph[nodes.get("F")][nodes.get("F")] = 0;
        graph[nodes.get("F")][nodes.get("B")] = 3;
        graph[nodes.get("F")][nodes.get("D")] = 7;
        graph[nodes.get("F")][nodes.get("E")] = 1;
//
//        for (int i = 0; i < GRAPH_SIZE; i++) {
//            for (int j = 0; j < GRAPH_SIZE; j++) {
//                System.out.print(graph[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public int[] dijkstra(String node) {
        int x = nodes.get(node);

        int[] result = new int[GRAPH_SIZE];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.MAX_VALUE;
        }

        int[] paths = new int[GRAPH_SIZE];
        boolean[] visited = new boolean[GRAPH_SIZE];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        boolean hasUnvisited = true;

        result[x] = 0;
        paths[x] = 0;

        while (hasUnvisited) {
            hasUnvisited = false;
            int shortestPath = Integer.MAX_VALUE;
            int currentCell = -1;

            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == true) {
                    continue;
                }
                if (result[i] < shortestPath) {
                    hasUnvisited = true;
                    shortestPath = result[i];
                    currentCell = i;
                }
            }

            if (!hasUnvisited) break;

            for (int i = 0; i < result.length; i++) {
                int newDistance = graph[currentCell][i] + result[currentCell];
                if (newDistance < result[i] && newDistance > -1) {
                    result[i] = newDistance;
                    paths[i] = currentCell;
                }
            }

            visited[currentCell] = true;
        }

        return result;
    }
}
