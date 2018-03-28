import java.util.Scanner;

public class Speeds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalCars = scanner.nextInt();
        int longestGroup = 1;
        int currentGroup = 1;
        int biggestTotalSpeed = scanner.nextInt();
        int currentTotalSpeed = biggestTotalSpeed;
        int currentLeaderSpeed = biggestTotalSpeed;

        for (int i = 1; i < totalCars; i++) {
            int nextCarSpeed = scanner.nextInt();
            if(nextCarSpeed > currentLeaderSpeed) {
                currentGroup++;
                currentTotalSpeed += nextCarSpeed;
                if(currentGroup == longestGroup) {
                    if (currentTotalSpeed > biggestTotalSpeed) {
                        biggestTotalSpeed = currentTotalSpeed;
                    }
                }
                if(currentGroup > longestGroup) {
                    longestGroup = currentGroup;
                    biggestTotalSpeed = currentTotalSpeed;
                }
            }

            else {
                currentTotalSpeed = nextCarSpeed;
                currentGroup = 1;
                currentLeaderSpeed = nextCarSpeed;
            }
        }

        System.out.println(biggestTotalSpeed);
    }
}
