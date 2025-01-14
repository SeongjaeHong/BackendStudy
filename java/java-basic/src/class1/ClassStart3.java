package class1;

public class ClassStart3 {
    public static void main(String[] args) {
        ProductOrder[] orders = new ProductOrder[3];
        orders[0] = new ProductOrder();
        orders[0].name = "Tofu";
        orders[0].price = 2000;
        orders[0].quantity = 2;

        orders[1] = new ProductOrder();
        orders[1].name = "Kimchi";
        orders[1].price = 5000;
        orders[1].quantity = 1;

        orders[2] = new ProductOrder();
        orders[2].name = "Coke";
        orders[2].price = 1500;
        orders[2].quantity = 2;

        int sum = 0;
        for(ProductOrder order: orders){
            System.out.printf("Product: %s, Price: %s, Quantity: %s\n",
                    order.name, order.price, order.quantity);
            sum += (order.price * order.quantity);
        }
        System.out.println("Price in total: " + sum);
    }
}
