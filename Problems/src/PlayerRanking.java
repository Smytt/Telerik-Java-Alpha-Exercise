import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerRanking {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();
        String input = reader.readLine();


        ArrayList<Player> players = new ArrayList<>(100000);
        LinkedList<Player> sortedByPosition = new LinkedList<>();
        HashMap<String, TreeSet<Player>> rankedByType = new HashMap<>();

        while (input.compareTo("end") != 0) {
            switch (input) {
                case "add":
                    addPlayer(reader, writer, players, rankedByType, sortedByPosition);
                    break;
                case "find":
                    findplayers(reader, writer, rankedByType);
                    break;
                case "ranklist":
                    ranklist(reader, writer, sortedByPosition);
                    break;
            }
            input = reader.readLine();
        }
        writer.close();

    }

    private static void ranklist(InputReader reader, OutputWriter writer, LinkedList<Player> sortedByPosition) {
        int start = reader.readInt();
        int end = reader.readInt();
        int[] counter = {start};

        String players = sortedByPosition.stream()
                .skip(start - 1)
                .limit(end - start + 1)
                .map(x -> counter[0]++ + ". " + x.toString())
                .collect(Collectors.joining("; "));

        writer.printLine(players);
    }

    private static void findplayers(InputReader reader, OutputWriter writer, HashMap<String, TreeSet<Player>> rankedByType) {
        String type = reader.readLine();
        String players = "";
        if (rankedByType.containsKey(type)) {
            players = rankedByType.get(type).stream()
                    .limit(5)
                    .map(x -> x.toString())
                    .collect(Collectors.joining("; "));
        }
        writer.printLine("Type", type + ":", players);
    }

    private static void addPlayer(InputReader reader, OutputWriter writer, ArrayList<Player> players, HashMap<String, TreeSet<Player>> rankedByType, LinkedList<Player> sortedByPosition) {
        Player player = new Player(reader.readLine(), reader.readLine(), reader.readInt(), reader.readInt());
        sortedByPosition.add(player.position - 1, player);

        rankByType(player, rankedByType);

        writer.printLine("Added player", player.name, "to position", player.position);

    }

    private static void rankByType(Player player, HashMap<String, TreeSet<Player>> rankedByType) {
        if (!rankedByType.containsKey(player.type)) {
            rankedByType.put(player.type, new TreeSet<Player>());
        }
        rankedByType.get(player.type).add(player);
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

class Player implements Comparable {
    String name;
    String type;
    int age;
    int position;

    Player(String name, String type, int age, int position) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.position = position;
    }

    @Override
    public int compareTo(Object o) {
        Player player = (Player) o;

        int compareNames = this.name.compareTo(player.name);
        if (compareNames == 0) {
            return Integer.compare(player.age, this.age);
        }
        return compareNames;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", name, age);
    }
}