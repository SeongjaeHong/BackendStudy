package nested.anonymous.ex1;

public abstract class Ex0Main {
    public abstract void code();
    public static void helloDice(Ex0Main func) {
        System.out.println("Start a program");

        func.code();

        System.out.println("End a program");
    }
    public static void main(String[] args) {
        Ex0Main main0 = new Ex0Main() {
            @Override
            public void code() {
                System.out.println("Code 1");
            }
        };
        helloDice(main0);

        Ex0Main main1 = new Ex0Main() {
            @Override
            public void code() {
                System.out.println("Code 2");
            }
        };
        helloDice(main1);
    }
}
