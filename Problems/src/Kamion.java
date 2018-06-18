import java.io.*;
import java.util.*;

public class Kamion {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();
        TreeSet<Branch> graph = new TreeSet<>();
        HashMap<Integer, Integer> treeGroups = new HashMap<>();

        int n = reader.readInt();
        int m = reader.readInt();

        for (int i = 0; i < m; i++) {
            graph.add(new Branch(reader.readInt(), reader.readInt(), reader.readInt()));
        }

        int maxTruck = graph.last().value;
        maxTruck = maxKruskal(graph, treeGroups, maxTruck);

        writer.print(maxTruck);
        writer.close();
    }

    private static int maxKruskal(TreeSet<Branch> graph, HashMap<Integer, Integer> treeGroups, int maxTruck) {
        for (Branch branch : graph) {
            int n1 = branch.n1;
            int n2 = branch.n2;
            int n1Root;
            int n2Root;
            if (treeGroups.containsKey(n1) && treeGroups.containsKey(n2)) {
                n1Root = treeGroups.get(n1);
                n2Root = treeGroups.get(n2);
                if (n1Root == n2Root) {
                    continue;
                }
                for (int key: treeGroups.keySet()) {
                    if (treeGroups.get(key) == n2Root) {
                        treeGroups.put(key, n1Root);
                    }
                }
            }

            else if (!treeGroups.containsKey(n1) && !treeGroups.containsKey(n2)) {
                treeGroups.put(n1, n1);
                treeGroups.put(n2, n1);
            }

            else if (treeGroups.containsKey(n1)) {
                n1Root = treeGroups.get(n1);
                treeGroups.put(n2, n1Root);
            }

            else if (treeGroups.containsKey(n2)) {
                n2Root = treeGroups.get(n2);
                treeGroups.put(n1, n2Root);
            }

            maxTruck = branch.value;
        }

        return maxTruck;
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

class Branch implements Comparable {
    int n1;
    int n2;
    int value;

    Branch(int n1, int n2, int value) {
        this.n1 = n1;
        this.n2 = n2;
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        Branch oldBranch = (Branch) o;
        int compareValue = Integer.compare(oldBranch.value, this.value);
        if (compareValue == 0) {
            return 1;
        }
        return compareValue;
    }

    @Override
    public String toString() {
        return String.format("%d %d %d", n1, n2, value);
    }
}
