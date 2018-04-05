import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessagesInBottle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String message = br.readLine();
        String cipher = br.readLine();
        HashMap<String, String> codes = new HashMap<>();
        HashSet<String> originalMessages = new HashSet<>();

        Pattern pattern = Pattern.compile("([A-Z]{1})([0-9]+)");
        Matcher matcher = pattern.matcher(cipher);

        while (matcher.find()) {
            codes.put(matcher.group(2), matcher.group(1));
        }

        findOriginalMessages(codes, originalMessages, message, "");

        ArrayList<String> sortedMessages = new ArrayList<>(originalMessages);
        Collections.sort(sortedMessages);
        System.out.println(sortedMessages.size());
        sortedMessages.forEach(System.out::println);
    }

    static void findOriginalMessages(HashMap<String, String> codes, HashSet<String> originalMessages, String message, String currentMessage) {
        if (message.length() == 0) {
            originalMessages.add(currentMessage);
        }

        for (int i = 1; i <= message.length(); i++) {
            String part = message.substring(0, i);
            if(!codes.containsKey(part)) {
                continue;
            };
            String remainingMessage = message.substring(i);
            findOriginalMessages(codes, originalMessages, remainingMessage, currentMessage + codes.get(part));
        }
    }
}
