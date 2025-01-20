package equals;

public class EqualsMain {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(10, 5);
        Rectangle rectangle2 = new Rectangle(10, 5);

        System.out.println(rectangle1.equals(rectangle2));
        System.out.println(rectangle1 == rectangle2);
    }
}
