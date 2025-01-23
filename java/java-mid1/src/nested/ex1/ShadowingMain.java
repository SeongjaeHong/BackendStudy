package nested.ex1;

public class ShadowingMain {
    public int value = 1;
    public static int v3 = 5;

    class Inner {
        public int value = 2;

        void go() {
            int value = 3;
            System.out.println("Value = " + value);
            System.out.println("this.Value = " + this.value);
            System.out.println("ShadowingMain.this.value = " + ShadowingMain.this.value);
        }
    }

    void test() {
        Inner in = new Inner();
        in.go();
    }

    public static void main(String[] args) {
        ShadowingMain main = new ShadowingMain();
        main.test();
    }
}
