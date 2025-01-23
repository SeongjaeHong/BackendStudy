package generic.ex1;

public class GenericMethod {
    <T> void objMethod2(T x) {
        System.out.println("This is not static: " + x);
    }

    public static Object objMethod(Object obj) {
        System.out.println("Object: " + obj);
        return obj;
    }

    public static <T> T genericMethod(T obj) {
        System.out.println("Object: " + obj);
        return obj;
    }

    public static <T extends Number> T genericBoundMethod(T obj) {
        System.out.println("Bound: " + obj);
        return obj;
    }

    public static void main(String[] args) {
        Integer i = 10;
        GenericMethod.objMethod(i);
        GenericMethod.<Integer>genericMethod(i);
        GenericMethod.<Double>genericBoundMethod(3.5);

        new GenericMethod().<String>objMethod2("si");
    }
}
