import java.io.*;
import java.util.*;

public class BojiGoal {
    static long minTime = Long.MAX_VALUE;
    static boolean found = false;

    public static void main(String[] args) {

        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();

        int players = reader.readInt();

        int startingPlayer = reader.readInt();

        Map<Integer, Map<Integer, Long>> graph = generateGraph(reader);

        if (!graph.containsKey(startingPlayer)) {
            graph.put(startingPlayer, new HashMap<>());
        }

        Map<Integer, Set<Integer>> memo = new HashMap<>();
        Set<Integer> visited = new TreeSet<>();

        if (graph.size() == 0) {
            System.out.println(0);
            return;
        }

        dfs(memo, graph, visited, startingPlayer, startingPlayer, 0);

        if (found)
            writer.printLine(minTime);
        else
            writer.printLine(0);
        writer.close();
    }

    private static void dfs(Map<Integer, Set<Integer>> memo, Map<Integer, Map<Integer, Long>> graph, Set<Integer> visited, int startingPlayer, int player, long time) {
        if (time >= minTime) {
            return;
        }
        visited.add(player);
        if (visited.size() == graph.size()) {
            if (graph.get(player).keySet().contains(startingPlayer)) {
                time += graph.get(player).get(startingPlayer);
                minTime = Math.min(minTime, time);
                found = true;
            }
            return;
        }
        Map<Integer, Long> adjacencyList = graph.get(player);
        for (int adjacent : adjacencyList.keySet()) {
            if (visited.contains(adjacent)) {
                continue;
            }
            if (inMemo(adjacent, visited, memo)) {
                continue;
            }
            dfs(memo, graph, visited, startingPlayer, adjacent, time + adjacencyList.get(adjacent));
            visited.remove(adjacent);
            addMemo(adjacent, visited, memo);
        }
    }

    private static boolean inMemo(int player, Set<Integer> visited, Map<Integer, Set<Integer>> memo) {
        if (!memo.containsKey(player) || visited.size() != memo.get(player).size()) {
            return false;
        }

        for (int visitedPlayer : visited) {
            if (!memo.get(player).contains(visitedPlayer)) {
                return false;
            }
        }

        return true;
    }

    private static void addMemo(int player, Set<Integer> visited, Map<Integer, Set<Integer>> memo) {
        if (!memo.containsKey(player)) {
            memo.put(player, new TreeSet<>());
        }
        memo.get(player).clear();
        memo.get(player).addAll(visited);
    }

    private static Map<Integer, Map<Integer, Long>> generateGraph(InputReader reader) {
        Map<Integer, Map<Integer, Long>> graph = new HashMap<>();

        while (true) {
            try {
                int p1 = reader.readInt();
                int p2 = reader.readInt();
                long t = reader.readLong();
                addTimes(graph, p1, p2, t);
            } catch (Exception e) {
                break;
            }
        }
        return graph;
    }

    private static void addTimes(Map<Integer, Map<Integer, Long>> graph, int p1, int p2, long t) {
        if (!graph.containsKey(p1)) {
            graph.put(p1, new HashMap<>());
        }
        if (!graph.containsKey(p2)) {
            graph.put(p2, new HashMap<>());
        }
        Map<Integer, Long> p1Adjacents = graph.get(p1);
        Map<Integer, Long> p2Adjacents = graph.get(p2);

        p1Adjacents.put(p2, t);
        p2Adjacents.put(p1, t);
    }


    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        InputReader() {
            this.stream = System.in;
        }

        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        long readLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        double readDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.' && c != ',') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, readInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.' || c == ',') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, readInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        String readLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        boolean isSpaceChar(int c) {
            return c == '\n' || c == '\r' || c == '\t' || c == -1 || c == ' ';
        }
    }

    static class OutputWriter {
        private final PrintWriter writer;

        OutputWriter() {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        }

        void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        void close() {
            writer.close();
        }
    }
}

