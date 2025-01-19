package Abstract1;

public class Cow implements InterfaceAnimal {

    @Override
    public void Sound() {
        System.out.println("Cow-cow");
    }

    @Override
    public void Move() {
        System.out.println("Move cow");
    }
}
