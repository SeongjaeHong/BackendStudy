package access.ex2;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items = new ArrayList<Item>(10);
    public void addItem(Item item) {
        if (items.size() < 10) {
            items.add(items.size(), item);
        }
        else {
            System.out.println("The cart is full. Cannot put in more items.");
        }
    }

    public void displayItems() {
        double sum = 0;
        System.out.println("Display items in the cart.");
        for (Item item: items) {
            sum += item.price * item.quantity;
            System.out.printf("%s £%.2f x%d\n", item.name, item.price, item.quantity);
        }
        System.out.printf("Total price: £%.2f\n", sum);
    }

    public static void main(String[] args) {
        ShoppingCart sc = new ShoppingCart();
        Item item1 = new Item("lettuce", 3.2, 4);
        Item item2 = new Item("galic", 2.1, 2);
        sc.addItem(item1);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);
        sc.addItem(item2);

        sc.displayItems();
    }
}
