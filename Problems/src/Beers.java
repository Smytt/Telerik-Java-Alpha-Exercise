import java.io.*;
import java.util.*;

public class Beers {
    static int shortestTime = Integer.MAX_VALUE;
    static int fieldR;
    static int fieldC;

    public static void main(String[] args) {
        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();

        int r = reader.readInt();
        int c = reader.readInt();

        fieldR = r;
        fieldC = c;
        int b = reader.readInt();

        TreeSet<Beer> allBeers = getBeers(reader, b);
        generateTree(allBeers);

        writer.printLine(shortestTime);

        writer.close();
    }

    private static TreeSet<Beer> getBeers(InputReader reader, int b) {
        TreeSet<Beer> allBeers = new TreeSet<>();
        while (b > 0) {
            b--;
            Beer beer = new Beer(reader.readInt(), reader.readInt());
            allBeers.add(beer);
        }
        return allBeers;
    }

    private static void generateTree(TreeSet<Beer> allBeers) {
        Queue<Beer> q = new ArrayDeque<>();
        Beer root = new Beer(0, 0);
        root.pathTillEnd = calculateToExit(root);

        for (Beer beer : allBeers) {
            Beer bestBeer = root;
            HashSet<Beer> used = new HashSet<>();
            q.offer(root);
            while (!q.isEmpty()) {
                Beer currentBeer = q.poll();
                used.add(currentBeer);
                if (beer.canFollowLeaf(currentBeer)) {
                    if (calculatePath(currentBeer, beer) + currentBeer.path < calculatePath(bestBeer, beer) + bestBeer.path) {
                        bestBeer = currentBeer;
                    }
                }
                for (Beer child: currentBeer.children) {
                    if (!used.contains(child)) {
                        q.offer(child);
                    }
                }
            }
            beer.attachToNode(bestBeer);
            beer.path = calculatePath(bestBeer, beer) + bestBeer.path;
            beer.pathTillEnd = calculateToExit(beer) + beer.path;
            shortestTime = Math.min(shortestTime, beer.pathTillEnd);
        }
    }

    private static int calculateToExit(Beer root) {
        return Math.abs(root.r - (fieldR -1)) + Math.abs(root.c - (fieldC -1));
    }

    private static int calculatePath(Beer root, Beer child) {
        int newPath = Math.abs(root.r - child.r) + Math.abs(root.c - child.c) - 4 - 1;
        return newPath;
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

class Beer implements Comparable {
    int r;
    int c;
    int path = 0;
    int pathTillEnd = 0;
    Beer prev = null;
    TreeSet<Beer> children = new TreeSet<>();

    Beer(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Object o) {
        Beer oldBeer = (Beer) o;
        int oldBeerDist = oldBeer.r + oldBeer.c;
        int thisDist = r + c;
        if (thisDist == oldBeerDist) return -1;
        return Integer.compare(thisDist, oldBeerDist);
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", r, c);
    }

    public boolean canFollowLeaf(Beer currentLeaf) {
        if (r >= currentLeaf.r && c >= currentLeaf.c) {
            return true;
        } else if (r >= currentLeaf.r && c == currentLeaf.c - 1) {
            return true;
        } else if (c >= currentLeaf.c && r == currentLeaf.r - 1) {
            return true;
        } else if (c >= currentLeaf.c && r == currentLeaf.r - 2) {
            return true;
        } else if (r >= currentLeaf.r && c == currentLeaf.c - 2) {
            return true;
        }
        return false;
    }

    public void attachToNode(Beer currentLeaf) {
        currentLeaf.children.add(this);
        this.prev = currentLeaf;
    }
}