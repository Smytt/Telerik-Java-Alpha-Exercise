import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws IOException {
        DecimalFormat dec = new DecimalFormat("0.00");
        BigDecimal bd = new BigDecimal("305879.725");
        BigDecimal hundred = new BigDecimal(100);
        System.out.println(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

}