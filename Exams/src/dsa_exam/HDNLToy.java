package dsa_exam;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Stack;

public class HDNLToy {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();
        Stack<Tag> s = new Stack<>();

        int n = reader.readInt();

        while (n > 0) {
            n--;

            Tag tag = new Tag(reader.readLine());
            if (s.isEmpty()) {
                printOpen(s, tag, writer);
                s.push(tag);
            } else {
                Tag lastTag = s.peek();
                while (tag.level <= lastTag.level && !s.isEmpty()) {
                    lastTag = s.pop();
                    printClose(s, lastTag, writer);
                    if (!s.isEmpty()) {
                        lastTag = s.peek();
                    }
                }
                printOpen(s, tag, writer);
                s.push(tag);
            }

        }

        while (!s.isEmpty()) {
            printClose(s, s.pop(), writer);
        }

        writer.close();
    }

    private static void printClose(Stack<Tag> s, Tag tag, OutputWriter writer) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < s.size(); i++) {
            line.append(" ");
        }
        line.append("</").append(tag.tag).append(">");
        writer.printLine(line);
    }

    private static void printOpen(Stack<Tag> s, Tag tag, OutputWriter writer) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < s.size(); i++) {
            line.append(" ");
        }
        line.append("<").append(tag.tag).append(">");
        writer.printLine(line);
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

class Tag {
    String tag;
    int level;

    Tag(String string) {
        this.tag = string;
        this.level = Integer.parseInt(string.substring(1, string.length()));
    }

    @Override
    public String toString() {
        return tag + " " + level;
    }
}
