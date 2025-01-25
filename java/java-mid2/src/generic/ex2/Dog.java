package generic.ex2;

public class Dog extends Animal{
    public Dog(String name, int size) {
        super(name, size);
    }

    @Override
    public void Sound() {
        System.out.println("Bow wow");
    }
}
