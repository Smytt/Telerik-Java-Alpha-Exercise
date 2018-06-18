import java.io.*;
import java.util.*;

public class Stools {
    static int maxHeight = 0;

    public static void main(String[] args) {
        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();
        TreeSet<Stool> stools = new TreeSet<>();

        int n = reader.readInt();

        while (n > 0) {
            n--;
            Stool stool = new Stool(reader.readInt(), reader.readInt(), reader.readInt());
            stools.add(stool);
        }

        findMax(stools, 0);

        writer.close();
    }

    private static void findMax(TreeSet<Stool> stools, int height) {
        for (Stool stool: stools) {
            if (stool.isUsed) continue;
            int currentHeight = stool.z;
            height += currentHeight;
            maxHeight = Math.max(maxHeight, height);
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

class Stool implements Comparable {
    int x, y, z;
    boolean isUsed = false;

    Stool(int x, int y, int z) {
        TreeSet<Integer> ordered = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Objects.equals(o1, o2)) {
                    return -1;
                }
                return Integer.compare(o1, o2);
            }
        });
        ordered.add(x);
        ordered.add(y);
        ordered.add(z);

        this.x = ordered.pollFirst();
        this.y = ordered.pollFirst();
        this.z = ordered.pollFirst();
    }

    void rotate() {
        int temp = x;
        x = y;
        y = z;
        z = temp;
    }

    @Override
    public int compareTo(Object o) {
        Stool oldStool = (Stool) o;
        int compareX = Integer.compare(this.x, oldStool.x);
        int compareY = Integer.compare(this.y, oldStool.y);
        int compareZ = Integer.compare(this.z, oldStool.z);
        if (compareX == 0) {
            if (compareY == 0) {
                return compareZ;
            }
            return compareY;
        }
        return  compareX;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", x, y, z);
    }
}
