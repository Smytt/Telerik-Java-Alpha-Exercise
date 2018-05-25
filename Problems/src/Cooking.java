import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Cooking {
    static void fakeInput() {
        String input = "";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Double> conversions = convertToMl();
        Map<String, Product> originalProducts = new HashMap<>();
        Map<String, Double> recipeProducts = new HashMap<>();
        Queue<String> recipeQueue = new LinkedList<>();

        getRecipeProducts(originalProducts, recipeProducts, recipeQueue, conversions, br);
        useProducts(recipeProducts, conversions, br);

        while (recipeQueue.size() > 0) {
            String product = recipeQueue.poll();

            if (recipeProducts.get(product) > 0) {
                printProduct(product, recipeProducts, originalProducts, conversions);
            }
        }
    }

    private static void useProducts(Map<String, Double> recipeProducts, Map<String, Double> conversions, BufferedReader br) throws IOException {
        int numUsed = Integer.parseInt(br.readLine());
        for (int i = 0; i < numUsed; i++) {
            String[] command = br.readLine().split(":");

            String product;
            if (command.length < 3) {
                product = null;
            } else {
                product = command[2].toLowerCase();
            }

            Double multiplier = conversions.get(command[1].toLowerCase());
            double amount = Double.parseDouble(command[0]) * multiplier;

            if (recipeProducts.containsKey(product)) {
                recipeProducts.put(product, recipeProducts.get(product) - amount);
            }
        }
    }

    private static void getRecipeProducts(Map<String, Product> originalProducts, Map<String, Double> recipeProducts, Queue<String> recipeQueue, Map<String, Double> conversions, BufferedReader br) throws IOException {
        int numRecipe = Integer.parseInt(br.readLine());
        for (int i = 0; i < numRecipe; i++) {
            String[] command = br.readLine().split(":");

            String originalProductName;
            String product;
            if (command.length < 3) {
                originalProductName = null;
                product = null;
            } else {
                originalProductName = command[2];
                product = originalProductName.toLowerCase();
            }

            String measurement = command[1];
            Double multiplier = conversions.get(measurement.toLowerCase());

            double amount = Double.parseDouble(command[0]) * multiplier;

            if (!recipeProducts.containsKey(product)) {
                recipeProducts.put(product, 0.0);
            }
            recipeProducts.put(product, recipeProducts.get(product) + amount);

            if (!originalProducts.containsKey(product)) {
                originalProducts.put(product, new Product(originalProductName, measurement));
                recipeQueue.offer(product);
            }
        }
    }

    private static Map<String,Double> convertToMl() {
        Map<String, Double> conversions = new HashMap<>();
        conversions.put("tsps", 5.0);
        conversions.put("tbsps", 15.0);
        conversions.put("ls", 1000.0);
        conversions.put("cups", 240.0);
        conversions.put("fl ozs", 30.0);
        conversions.put("pts", 480.0);
        conversions.put("qts", 960.0);
        conversions.put("gals", 3840.0);
        conversions.put("mls", 1.0);
        conversions.put("teaspoons", 5.0);
        conversions.put("tablespoons", 15.0);
        conversions.put("liters", 1000.0);
        conversions.put("fluid ounces", 30.0);
        conversions.put("pints", 480.0);
        conversions.put("quarts", 960.0);
        conversions.put("gallons", 3840.0);
        conversions.put("milliliters", 1.0);

        return conversions;
    }

    private static void printProduct(String product, Map<String, Double> recipeProducts, Map<String, Product> originalProducts, Map<String, Double> conversions) {
        DecimalFormat dec = new DecimalFormat("0.00");
        String originalName = originalProducts.get(product).name;
        String measurement = originalProducts.get(product).measurement;
        double amount = (double) recipeProducts.get(product) / conversions.get(measurement.toLowerCase());
        BigDecimal bd = new BigDecimal(String.valueOf(amount));
        System.out.println(bd.setScale(2, BigDecimal.ROUND_HALF_UP) + ":" + measurement + ":" + originalName);

    }
}

class Product {
    String name;
    String measurement;

    Product(String name, String measurement) {
        this.name = name;
        this.measurement = measurement;
    }
}
