import java.util.*;
import java.util.stream.*;

class Product {
    String name, category;
    double price;
    
    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 167200),
            new Product("Phone", "Electronics", 11800),
            new Product("Shirt", "Clothing", 1400),
            new Product("Jeans", "Clothing", 3600)
        );
        
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category, 
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));

        mostExpensiveByCategory.forEach((category, product) ->
            System.out.println(category + " - Most Expensive: " + product.get().name));
        
        double avgPrice = products.stream()
                .mapToDouble(p -> p.price)
                .average()
                .orElse(0);
        
        System.out.println("Average Price of all products: " + avgPrice);
    }
}
