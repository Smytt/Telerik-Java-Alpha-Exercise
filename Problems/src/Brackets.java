import java.util.HashSet;
import java.util.stream.Stream;

public class Brackets {
    public static void main(String[] args) {
        generateBracketsExpressions("", 8)
                .forEach(System.out::println);
    }

    static HashSet<String> generateBracketsExpressions(String exp, int n) {
        HashSet<String> bracketsExressions = new HashSet<>();
        if (exp.length() >= n) {
            bracketsExressions.add(exp);
            return bracketsExressions;
        }
        HashSet<String> result = generateBracketsExpressions('(' + exp + ')', n);
        bracketsExressions.addAll(result);

        generateBracketsExpressions("()" + exp, n)
                .forEach(bracketsExressions::add);
        generateBracketsExpressions(exp + "()", n)
                .forEach(bracketsExressions::add);

        return bracketsExressions;
    }
}
