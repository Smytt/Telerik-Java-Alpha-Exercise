package dsa_exam;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class OnlineMarket {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TreeSet<Product> allProducts = new TreeSet<>();
        HashMap<String, TreeSet<Product>> byType = new HashMap<>();

        boolean end = false;

        while (!end) {
            String[] command = br.readLine().split(" ");

            switch (command[0]) {
                case "add":
                    add(allProducts, byType, command);
                    break;
                case "filter":
                    filter(allProducts, byType, command);
                    break;
                case "end":
                    end = true;
                    break;
                default:
                    System.out.println("err");
            }
        }
    }

    private static void filter(TreeSet<Product> allProducts, HashMap<String, TreeSet<Product>> byType, String[] command) {

        String filter = command[2];

        switch (filter) {
            case "type":
                filterByType(byType, command);
                break;
            case "price":
                filterByPrice(allProducts, command);
                break;
            default:
                System.out.println("err");
        }

    }

    private static void filterByPrice(TreeSet<Product> allProducts, String[] command) {
        String limiter = command[3];
        switch (limiter) {
            case "to": {
                BigDecimal maxPrice = new BigDecimal(command[4]);
                String listedProducts = allProducts.stream()
                        .filter(x -> x.price.compareTo(maxPrice) == 0 || x.price.compareTo(maxPrice) == -1)
                        .sorted()
                        .limit(10)
                        .map(Product::toString)
                        .collect(Collectors.joining(", "));
                System.out.println("Ok: " + listedProducts);
            }
            break;
            case "from": {
                BigDecimal minPrice = new BigDecimal(command[4]);
                if (command.length == 5) {
                    String listedProducts = allProducts.stream()
                            .filter(x -> x.price.compareTo(minPrice) == 0 || x.price.compareTo(minPrice) == 1)
                            .sorted()
                            .limit(10)
                            .map(Product::toString)
                            .collect(Collectors.joining(", "));
                    System.out.println("Ok: " + listedProducts);
                    break;
                } else {
                    BigDecimal maxPrice = new BigDecimal(command[6]);
                    String listedProducts = allProducts.stream()
                            .filter(x -> x.price.compareTo(minPrice) == 0 || x.price.compareTo(minPrice) == 1)
                            .filter(x -> x.price.compareTo(maxPrice) == 0 || x.price.compareTo(maxPrice) == -1)
                            .sorted()
                            .limit(10)
                            .map(Product::toString)
                            .collect(Collectors.joining(", "));
                    System.out.println("Ok: " + listedProducts);
                }
            }
            break;
            default:
                System.out.println("err");
        }
    }

    private static void filterByType(HashMap<String, TreeSet<Product>> byType, String[] command) {
        String type = command[3];
        if (!byType.containsKey(type)) {
            System.out.println("Error: Type " + type + " does not exists");
            return;
        }
        String listedProducts = byType.get(type)
                .stream()
                .sorted()
                .limit(10)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.println("Ok: " + listedProducts);
    }

    private static void add(TreeSet<Product> allProducts, HashMap<String, TreeSet<Product>> byType, String[] command) {
        if (command[1].length() > 20 || command[1].length() < 3 || command[3].length() > 20 || command[3].length() < 3) {
            return;
        }
        Product product = new Product(command[1], new BigDecimal(command[2]), command[3]);

        if (!allProducts.contains(product)) {
            allProducts.add(product);
            System.out.println("Ok: Product " + product.name + " added successfully");
            addToType(product, byType);
            return;
        }
        System.out.println("Error: Product " + product.name + " already exists");
    }


    private static void addToType(Product product, HashMap<String, TreeSet<Product>> byType) {
        if (!byType.containsKey(product.type)) {
            byType.put(product.type, new TreeSet<>());
        }
        byType.get(product.type).add(product);
    }
}


class Product implements Comparable {
    String name;
    BigDecimal price;
    String type;

    Product(String name, BigDecimal price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Override
    public String toString() {
        String priceString = String.valueOf(Double.valueOf(price.toString()));
        while (priceString.charAt(priceString.length() - 1) == '0') {
            priceString = priceString.substring(0, priceString.length() - 1);
        }
        if (priceString.charAt(priceString.length() - 1) == '.') {
            priceString = priceString.substring(0, priceString.length() - 1);
        }
        return name + "(" + priceString + ")";
    }

    @Override
    public int compareTo(Object o) {
        Product oldProduct = (Product) o;
        if (this.name.compareTo(oldProduct.name) == 0) {
            return 0;
        }
        if (this.price.compareTo(oldProduct.price) == -1) {
            return -1;
        }
        if (this.price.compareTo(oldProduct.price) == 1) {
            return 1;
        }

        return this.name.compareTo(oldProduct.name);
    }
}