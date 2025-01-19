package poly.ex2;

public class Chicken extends AbstractAnimal implements Fly {
    @Override
    public void sound() {
        System.out.println("Chick-chick");
    }

    @Override
    public void fly() {
        System.out.println("Chicken flies");
    }
}
