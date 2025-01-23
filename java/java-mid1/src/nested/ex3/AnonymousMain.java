package nested.ex3;

public class AnonymousMain {
    public static void main(String[] args) {
        Hello h = new Hello() {
            @Override
            public void hello() {
                System.out.println("Hello");
            }
        };
        h.hello();
    }
}
