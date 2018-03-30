import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JoroTheRabbit {
    public static void main(String[] args) throws IOException {
//        Scanner s = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> stringNumbers = Arrays.asList(br.readLine().split(", "));
        ArrayList<Integer> numbers = new ArrayList<Integer>(2500);
        HashMap<Integer, HashSet<Integer>> steppedOn = new HashMap<>();

        for (String num : stringNumbers) {
            numbers.add(Integer.parseInt(num));
        }

        int longestSequence = 0;
        ArrayList<Integer> hopSequence = new ArrayList<>(2500);

        for (int i = 0; i < numbers.size(); i++) {
            for (int hop = 1; hop < numbers.size(); hop++) {
                hopSequence.clear();
                boolean continueHopping = true;
                int currentIndex = i;

                while (continueHopping) {
                    if(!steppedOn.containsKey(currentIndex)) {
                        steppedOn.put(currentIndex, new HashSet<>());
                    }

                    if (steppedOn.get(currentIndex).contains(hop)) {
                        continueHopping = false;
                        continue;
                    }

                    if (hopSequence.size() > 0) {
                        int lastIndex = hopSequence.get(hopSequence.size() - 1);
                        if (numbers.get(lastIndex) >= numbers.get(currentIndex)) {
                            continueHopping = false;
                            continue;
                        }
                    }

                    steppedOn.get(currentIndex).add(hop);
                    hopSequence.add(currentIndex);
                    currentIndex += hop;
                    currentIndex = currentIndex % numbers.size();
                    if (hopSequence.size() > longestSequence) {
                        longestSequence = hopSequence.size();
                    }
                }
            }
        }

        System.out.println(longestSequence);
    }
}