import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Diameter {

    static int bestNodeA = 0;

    public static void main(String[] args) {
        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();
        int n = reader.readInt();
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        HashSet<Integer> used = new HashSet<>();


        for (int i = 0; i < n - 1; i++) {
            int node1 = reader.readInt();
            int node2 = reader.readInt();
            int weight = reader.readInt();

            if(!graph.containsKey(node1)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(node1);
                list.add(0);
                graph.put(node1, list);
            }
            if(!graph.containsKey(node2)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(node2);
                list.add(0);
                graph.put(node2, list);
            }
            graph.get(node1).add(node2);
            graph.get(node1).add(weight);
            graph.get(node2).add(node1);
            graph.get(node2).add(weight);
        }

        dfs(n-1, n-1, graph, used, 0);
        int node = bestNodeA;
        used = new HashSet<>();
        dfs(node, node, graph, used, 0);
        System.out.println(graph.get(node).get(1));
        writer.close();
    }

    private static void dfs(int originalNode, int i, HashMap<Integer, ArrayList<Integer>> graph, HashSet<Integer> used, int currentLength) {
        used.add(i);
        List<Integer> node = graph.get(i);
        for (int child = 2; child < node.size(); child += 2) {
            int childNode = node.get(child);
            int weight = node.get(child + 1);
            if (!used.contains(childNode)) {
                int newLength = currentLength + weight;
                dfs(originalNode, childNode, graph, used, newLength);
            }
        }

        int maxLength = graph.get(originalNode).get(1);

        if (currentLength > maxLength) {
            graph.get(originalNode).set(1, currentLength);
            bestNodeA = i;
        }
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
