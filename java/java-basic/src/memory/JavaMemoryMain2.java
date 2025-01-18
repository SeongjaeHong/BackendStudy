package memory;

public class JavaMemoryMain2 {
    public static void main(String[] args) {
        System.out.println("Start main");
        method1();
        System.out.println("End main");
    }

    static void method1() {
        System.out.println("Start method1");
        Data data1 = new Data(5);
        method2(data1);
        System.out.println("End method1");
    }

    static void method2(Data data) {
        System.out.println("Start method2");
        System.out.println("data.value = " + data.getValue());
        System.out.println("End method2");
    }
}
