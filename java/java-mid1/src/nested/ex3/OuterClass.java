package nested.ex3;

public class OuterClass {
    public void myMethod() {
        class LocalClass {
            public void hello() {
                System.out.println("Hello");
            }
        }
        new LocalClass().hello();
    }
}
