package poly.ex2;

public class Bird extends AbstractAnimal implements Fly {
    @Override
    public void sound() {
        System.out.println("Frrrr");
    }

    @Override
    public void fly() {
        System.out.println("Bird flies");
    }
}
