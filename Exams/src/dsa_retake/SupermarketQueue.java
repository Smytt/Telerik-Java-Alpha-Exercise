package dsa_retake;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SupermarketQueue {
    static boolean go = true;
    static int waiting = 0;

    public static void main(String[] args) {
        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();
        LinkedList<String> q = new LinkedList<>();
        LinkedList<Person> inserted = new LinkedList<>();

        HashMap<String, Integer> names = new HashMap<>();

        while (go) {
            String command = reader.readLine();

            switch (command) {
                case "Append":
                    append(reader.readLine(), q, names, writer);
                    break;
                case "Insert":
                    insert(reader.readInt(), reader.readLine(), q, names, writer, inserted);
                    break;
                case "Find":
                    find(reader.readLine(), names, writer);
                    break;
                case "Serve":
                    serve(reader.readInt(), q, names, writer, inserted);
                    break;
                case "End":
                    go = false;
                    break;
            }
        }

        writer.close();

    }

    private static void serve(int people, LinkedList<String> q, HashMap<String, Integer> names, OutputWriter writer, LinkedList<Person> inserted) {
        if (people > q.size()) {
            writer.printLine("Error");
            return;
        }
        ArrayList<String> serve = new ArrayList<>(500);

        while (people > 0) {
            boolean skip = false;
            people--;
            if (!inserted.isEmpty()) {
                waiting--;
                if (waiting == 0) {
                    skip = true;
                    Person person = inserted.removeFirst();
                    if (!inserted.isEmpty()) {
                        waiting = inserted.peekFirst().wait;
                    }
                    serve.add(person.name);
                    removeFromListOfNames(person.name, names);
                    continue;
                }
            }
            if (!skip) {
                String person = q.removeFirst();
                serve.add(person);
                removeFromListOfNames(person, names);
            }
        }
        writer.printLine(serve.stream().collect(Collectors.joining(" ")));
    }

    private static void removeFromListOfNames(String name, HashMap<String, Integer> names) {
        names.put(name, names.get(name) - 1);
    }

    private static void find(String name, HashMap<String, Integer> names, OutputWriter writer) {
        int matches = 0;
        if (names.containsKey(name)) {
            matches = names.get(name);
        }
        writer.printLine(matches);
    }

    private static void insert(int i, String name, LinkedList<String> q, HashMap<String, Integer> names, OutputWriter writer, LinkedList<Person> inserted) {
        if (i < 0 || i > q.size()) {
            writer.printLine("Error");
            return;
        }

        if (inserted.isEmpty()) {
            waiting = i;
            inserted.addLast(new Person(name, 0));
        } else {
            int lastWaiting = inserted.peekLast().wait;
            inserted.addLast(new Person(name, i - lastWaiting - waiting));
        }

        addToListOfNames(name, names);
        writer.printLine("OK");
    }

    private static void append(String name, LinkedList<String> q, HashMap<String, Integer> names, OutputWriter writer) {
        q.addLast(name);
        addToListOfNames(name, names);
        writer.printLine("OK");
    }

    private static void addToListOfNames(String name, HashMap<String, Integer> names) {
        if (!names.containsKey(name)) {
            names.put(name, 0);
        }
        names.put(name, names.get(name) + 1);
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

        String readLineIgnoreSpace() {
            int c = read();
            while (isSpaceChar2(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar2(c));
            return res.toString();
        }

        boolean isSpaceChar(int c) {
            return c == '\n' || c == '\r' || c == '\t' || c == -1 || c == ' ';
        }

        boolean isSpaceChar2(int c) {
            return c == '\n' || c == '\r' || c == '\t' || c == -1;
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

class Person {
    String name;
    int wait;

    Person(String name, int wait) {
        this.name = name;
        this.wait = wait;
    }
}