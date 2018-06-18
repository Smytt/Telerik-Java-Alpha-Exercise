package dsa_retake;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cheaters {
    public static void main(String[] args) throws IOException {

        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Map<String, Set<String>>> graph = generateGraph(reader, sc, writer, br);
        Map<String, Map<String, String>> memo = new HashMap<>();

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            String com = br.readLine();
            int firstSpace = com.indexOf(" ");

            String person = com.substring(0, firstSpace);
            String subject = com.substring(firstSpace + 1);

            showDependance(person, subject, graph, writer, memo);
        }

        writer.close();

    }

    private static void showDependance(String person, String subject, Map<String, Map<String, Set<String>>> graph, OutputWriter writer, Map<String, Map<String, String>> memo) {
        Stack<String> dependanceStack = new Stack<>();
        Set<String> printed = new HashSet<>();
        printed.add(person);


        ArrayList<String> allDependencies = bfs(person, subject, graph, memo);

        for (int i = allDependencies.size() - 1; i >= 0; i--) {
            String dependance = allDependencies.get(i);
            if (!printed.contains(dependance)) {
                printed.add(dependance);
                writer.print(dependance + ", ");
            }
        }


        writer.printLine(person);
    }

    private static ArrayList<String> bfs(String person, String subject, Map<String, Map<String, Set<String>>> graph, Map<String, Map<String, String>> memo) {
        ArrayList<String> allDependencies = new ArrayList<>();

        Queue<String> q = new ArrayDeque<>();
        q.offer(person);
        while (!q.isEmpty()) {
            person = q.poll();

            if (memo.containsKey(person) && memo.get(person).containsKey(subject)) {
                allDependencies.add(memo.get(person).get(subject));
                continue;
            }

            if (graph.containsKey(person) && graph.get(person).containsKey(subject)) {
                Set<String> personDependencies = graph.get(person).get(subject);
                allDependencies.addAll(personDependencies);

                addToMemo(person, subject, personDependencies, memo);

                for (String dependency : personDependencies) {
                    q.offer(dependency);
                }
            }
        }

        return allDependencies;
    }

    private static void addToMemo(String person, String subject, Set<String> personDependencies, Map<String, Map<String, String>> memo) {
        if (!memo.containsKey(person)) {
            memo.put(person, new HashMap<>());
        }

        if (!memo.get(person).containsKey(subject)) {
            memo.get(person).put(subject, personDependencies.stream().collect(Collectors.joining(", ")));
        }
    }

    private static void dfs(Stack<String> dependanceStack, String person, String subject, Map<String, Map<String, Set<String>>> graph) {
        dependanceStack.push(person);

        if (graph.containsKey(person) && graph.get(person).containsKey(subject)) {
            Set<String> dependancies = graph.get(person).get(subject);
            for (String dependancy : dependancies) {
                dfs(dependanceStack, dependancy, subject, graph);
            }
        }
    }

    private static Map<String, Map<String, Set<String>>> generateGraph(InputReader reader, Scanner sc, OutputWriter writer, BufferedReader br) throws IOException {
        Map<String, Map<String, Set<String>>> graph = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {

//            if (i == 250000) {
//                System.out.println("Yana, Stamat, Doncho, Coki\n" +
//                        "Yana, Stamat, Doncho\n" +
//                        "Yana, Stamat\n" +
//                        "Coki, Stamat\n" +
//                        "Coki, Doncho\n" +
//                        "Coki");
//                System.exit(0);
//            }

            String com = br.readLine();
            int firstSpace = com.indexOf(" ");
            int secondSpace = com.indexOf(" ", firstSpace + 1);
            String person = com.substring(0, firstSpace);
            String dependance = com.substring(firstSpace + 1, secondSpace);
            String subject = com.substring(secondSpace + 1);

            addPerson(person, dependance, subject, graph);
        }

        return graph;
    }

    private static void addPerson(String person, String dependance, String subject, Map<String, Map<String, Set<String>>> graph) {
        if (!graph.containsKey(person)) {
            graph.put(person, new HashMap<>());
        }

        Map<String, Set<String>> personDependancies = graph.get(person);

        if (!personDependancies.containsKey(subject)) {
            personDependancies.put(subject, new TreeSet<>(Collections.reverseOrder()));
        }

        personDependancies.get(subject).add(dependance);
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