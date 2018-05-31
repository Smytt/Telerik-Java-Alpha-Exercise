import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnitsOfWork {
    static void fakeInput() {
        String input = "add TheMightyThor God 100\n" +
                "add Artanis Protoss 250\n" +
                "add Fenix Protoss 200\n" +
                "add Spiderman MutatedHuman 180\n" +
                "add XelNaga God 500\n" +
                "add Wolverine MutatedHuman 200\n" +
                "add Zeratul Protoss 300\n" +
                "add Spiderman MutatedHuman 180\n" +
                "power 3\n" +
                "find Protoss\n" +
                "find God\n" +
                "remove Kerrigan\n" +
                "remove XelNaga\n" +
                "power 3\n" +
                "find Kerrigan\n" +
                "find God\n" +
                "end\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private static HashMap<String, Unit> unitsMap = new HashMap<>();
    private static HashMap<String, TreeSet<Unit>> orderedUnitsByType = new HashMap<>();
    private static TreeSet<Unit> orderedUnits = new TreeSet<>();

    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (true) {
            command = br.readLine();
            String[] params = command.split(" ");

            switch (params[0]) {
                case "end":
                    end();
                    return;
                case "add":
                    add(params);
                    break;
                case "remove":
                    remove(params);
                    break;
                case "find":
                    find(params);
                    break;
                case "power":
                    power(params);
                    break;
            }
        }
    }

    private static void end() {
        System.out.println(output);
    }

    private static void power(String[] params) {
        int numberOfUnits = Integer.parseInt(params[1]);

        String listed = "";
        if (orderedUnits.size() > 0) {
            listed = orderedUnits.stream()
                    .limit(numberOfUnits)
                    .map(Unit::toString)
                    .collect(Collectors.joining(", "));
        }

        output
                .append("RESULT: ")
                .append(listed)
                .append("\n");
    }

    private static void find(String[] params) {
        String listed = "";
        if (orderedUnitsByType.containsKey(params[1])) {
            listed = orderedUnitsByType.get(params[1]).stream()
                    .limit(10)
                    .map(Unit::toString)
                    .collect(Collectors.joining(", "));
        }
        output
                .append("RESULT: ")
                .append(listed)
                .append("\n");
    }

    private static void add(String[] params) {
        if (unitsMap.containsKey(params[1])) {
            output
                    .append("FAIL: ")
                    .append(params[1])
                    .append(" already exists!\n");
        } else {
            Unit unit = new Unit(params[1], params[2], Integer.parseInt(params[3]));
            unitsMap.put(params[1], unit);

            if (!orderedUnitsByType.containsKey(params[2])) {
                orderedUnitsByType.put(params[2], new TreeSet<>());
            }
            orderedUnitsByType.get(params[2]).add(unit);

            orderedUnits.add(unit);

            output
                    .append("SUCCESS: ")
                    .append(params[1])
                    .append(" added!\n");
        }
    }

    private static void remove(String[] params) {
        if (!unitsMap.containsKey(params[1])) {
            output
                    .append("FAIL: ")
                    .append(params[1])
                    .append(" could not be found!\n");
        } else {
            Unit unit = unitsMap.get(params[1]);
            unitsMap.remove(unit.name);
            orderedUnitsByType.get(unit.type).remove(unit);
            orderedUnits.remove(unit);

            output
                    .append("SUCCESS: ")
                    .append(params[1])
                    .append(" removed!\n");
        }
    }
}

class Unit implements Comparable {
    String name;
    String type;
    int attack;

    Unit(String name, String type, int attack) {
        this.name = name;
        this.type = type;
        this.attack = attack;
    }

    @Override
    public String toString() {
        return String.format("%s[%s](%s)", name, type, attack);
    }

    @Override
    public int compareTo(Object o) {
        Unit unit = (Unit) o;
        int compareAttack = Integer.compare(unit.attack, this.attack);
        if (compareAttack == 0) {
            return this.name.compareTo(unit.name);
        }
        return compareAttack;
    }
}
