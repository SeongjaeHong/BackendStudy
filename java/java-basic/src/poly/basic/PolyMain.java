package poly.basic;

public class PolyMain {
    public static void main(String[] args) {
        Child child = new Child();
        System.out.println("Child value: " + child.value);
        child.Method();

        Parent parent = new Parent();
        System.out.println("Parent value: " + parent.value);
        parent.Method();

        Parent poly = new Child();
        System.out.println("Poly value: " + poly.value); // Value can't be overrode.
        poly.Method();
    }
}
