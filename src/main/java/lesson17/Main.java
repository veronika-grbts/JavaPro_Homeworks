package lesson17;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = ProductRepository.getData();

        Map<String, List<Product>> group = products.stream()
               .collect(Collectors.groupingBy(Product::getCategory));

        group.forEach((key, value) -> {
            System.out.println("Product category: " + key);
            value.forEach(product -> {
                System.out.println("Product name: " + product.getName() + " price: " + product.getPrice());
            });
        });

       Double avgFruit = products.stream()
               .filter(product -> product.getCategory().equals("fruit"))
               .collect(Collectors.averagingDouble(Product::getPrice));

        Double avgVegetable = products.stream()
                .filter(product -> product.getCategory().equals("vegetable"))
                .collect(Collectors.averagingDouble(Product::getPrice));

        System.out.println("average price for fruit = "+avgFruit);
        System.out.println("average price for vegetable = "+avgVegetable);

        products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(maxCategory ->
                        System.out.println("Category with highest average price: " + maxCategory.getKey())
                );
    }
}
