package dijkstra;

public class Main {
    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        int[] result = d.dijkstra("A");

        for(int i = 0; i< result.length; i++) {
            System.out.print(result[i] + " - ");

        }
    }
}
