package lesson17;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    
    public  static List<Product> getData(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("orange", "fruit", 12.5));
        products.add(new Product("potato", "vegetable", 7));
        products.add(new Product("tomato", "vegetable", 3.5));
        products.add(new Product("apple", "fruit", 2.5));
        products.add(new Product("banana", "fruit", 22.1));
        products.add(new Product("zucchini", "vegetable", 8.5));
        products.add(new Product("cabbage", "vegetable", 18.5));
        return products;
    }
}
