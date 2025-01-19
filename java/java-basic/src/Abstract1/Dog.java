package Abstract1;

public class Dog implements InterfaceAnimal{
    @Override
    public void Sound() {
        System.out.println("Doggy-dog");
    }

    @Override
    public void Move() {
        System.out.println("Move dog");
    }
}
