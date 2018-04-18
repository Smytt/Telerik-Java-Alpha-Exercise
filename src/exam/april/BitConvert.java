package exam.april;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BitConvert {
    static void fakeInput() {
        String input = "2,1,0,2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        String[] inputCode = br.readLine().split(",");

        int queue = 0;

        for (int i = inputCode.length - 1; i > -1; i--) {
            int decimal = Integer.parseInt(inputCode[i]);
            String binary = Integer.toString(decimal, 2);

            for (int j = binary.length() - 1; j > -1; j--) {
                if (i % 2 == 0) {
                    if (binary.length() % 2 == 0) {
                        if (j % 2 != 0) {
                            result.insert(0, binary.charAt(j));
                        } else continue;
                    } else {
                        if (j % 2 == 0) {
                            result.insert(0, binary.charAt(j));
                        } else continue;
                    }
                } else {
                    if (binary.length() % 2 == 0) {
                        if (j % 2 != 0) {
                            continue;
                        } else result.insert(0, binary.charAt(j));
                        ;
                    } else {
                        if (j % 2 == 0) {
                            continue;
                        } else result.insert(0, binary.charAt(j));
                        ;
                    }
                }

            }

            while (result.length() == 0 || result.length() % 4 != 0 || result.length() == queue * 4) {
                result.insert(0, 0);
            }
            queue++;
        }

        System.out.println(result);
    }

}
