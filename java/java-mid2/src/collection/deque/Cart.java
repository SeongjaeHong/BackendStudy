package collection.deque;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public void add(Product product, int quantity) {
        int quantityBefore = items.getOrDefault(product, 0);
        int quantityAfter = quantityBefore + quantity;
        items.put(product, quantityAfter);
    }

    public void printAll() {
        System.out.println("==Print out all items==");
        for (Map.Entry<Product, Integer> item: items.entrySet()) {
            System.out.println(item.getKey() + " Quantity: " + item.getValue());
        }
    }

    public void minus(Product product, int quantity) {
        int quantityBefore = items.getOrDefault(product, 0);
        int quantityAfter = quantityBefore - quantity;
        if (quantityAfter > 0) {
            items.put(product, quantityAfter);
        } else {
            items.remove(product);
        }

    }
}
