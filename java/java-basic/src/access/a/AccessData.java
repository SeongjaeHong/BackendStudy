package access.a;

public class AccessData {
    public int publicField;
    int defaultField;
    private int privateField;

    public void publicMethod() {
        System.out.println("PublicMethod: " + publicField);
    }

    void defaultMethod() {
        System.out.println("DefaultMethod: " + defaultField);
    }

    private void privateMethod() {
        System.out.println("PrivateMethod: " + privateField);
    }

    public void innerAccess() {
        System.out.println("Inner Call");
        publicField = 100;
        defaultField = 200;
        privateField = 300;
        publicMethod();
        defaultMethod();
        privateMethod();
    }
}
