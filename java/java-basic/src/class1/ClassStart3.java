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

        System.out.println(orders[1].price);
        changeValue(orders[1], 6900);
        System.out.println(orders[1].price);

        int value = getValue(orders[0]);
        System.out.println(value);
    }

    public static void changeValue(ProductOrder po, int price){
        po.price = price;
    }

    public static int getValue(ProductOrder po) {
        return po.price;
    }
}

