package april;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecretMessage {
    static void fakeInput() {
        String input = "a3{cd2{a}f}ef";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String message = br.readLine();

        findMessage(message);
    }

    private static void findMessage(String message) {
        int closeIndex = message.indexOf('}');

        if(closeIndex == -1) {
            System.out.printf(message);
            return;
        }

        int startIndex = message.substring(0, closeIndex).lastIndexOf('{');
        String code = message.substring(startIndex + 1, closeIndex);
        String checkRepeatTimes = message.substring(0, startIndex);
        String before = "";
        String after = message.substring(closeIndex + 1, message.length());
        int repeatTimes = 0;
        for (int i = 1; i <= checkRepeatTimes.length(); i++) {
            char character = checkRepeatTimes.charAt(checkRepeatTimes.length() - i);
            if (Character.isDigit(character)) {
                repeatTimes += Math.pow(10, i - 1) * (character - '0');
            } else {
                before = message.substring(0, startIndex - i + 1);
                break;
            }
        }

        StringBuilder newMessage = new StringBuilder();
        newMessage.append(before);
        for (int i = 1; i <= repeatTimes; i++) {
            newMessage.append(code);
        }
        newMessage.append(after);
        message = newMessage.toString();
        findMessage(message);
    }
}
