import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Swappings {
    static void fakeInput() {
        String input = "4\n" +
                "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Node> indexedNodes = new ArrayList<>();
        indexedNodes.add(new Node(-1));

        Node first = new Node(1);
        Node last = first;
        indexedNodes.add(last);

        for (int i = 2; i < n + 1; i++) {
            Node newNode = new Node(i);
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
            indexedNodes.add(last);

        }

        String[] numbers = br.readLine().split(" ");

        for (int i = 0; i < numbers.length; i++) {
            int number = Integer.parseInt(numbers[i]);
            Node anchor = indexedNodes.get(number);
            Node ghost;

            if (number == last.number) {
                ghost = new Node(-1);
                last.next = ghost;
                ghost.prev = last;
                last = ghost;
            }
            if (number == first.number) {
                ghost = new Node(-1);
                first.prev = ghost;
                ghost.next = first;
                first = ghost;
            }

            Node newFirst = anchor.next;
            Node newLast = anchor.prev;

            newFirst.prev = null;
            newLast.next = null;

            anchor.prev = last;
            anchor.next = first;

            first.prev = anchor;
            last.next = anchor;

            first = newFirst;
            last = newLast;

            if (first.number == -1) {
                first = first.next;
                first.prev = null;
            }
            if (last.number == -1) {
                last = last.prev;
                last.next = null;
            }

        }

        StringBuilder result = new StringBuilder();
        while (first != null) {

            result.append(first).append(" ");
            first = first.next;
        }
        System.out.println(result);
    }
}

class Node {
    int number;
    Node prev = null;
    Node next = null;

    Node(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
