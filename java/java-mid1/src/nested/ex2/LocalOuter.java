package nested.ex2;

public class LocalOuter {
    int outInstanceVar = 3;
    public Printer process(int paramVar) { // paramVar should be final type.
        int localVar = 1;  // localVar should be final type.

        class LocalPrinter implements Printer {
            int value = 0;

            @Override
            public void print() {
                System.out.println("value=" + value);

                System.out.println("localVar=" + localVar);  // Because they are used here within inner class.
                System.out.println("paramVar=" + paramVar);
                System.out.println("outInstanceVar=" + outInstanceVar);
            }
        }

        return new LocalPrinter();
    }

    public static void main(String[] args) {
        LocalOuter lo = new LocalOuter();
        Printer pr = lo.process(2);
        pr.print();

    }
}
