package generic.ex2;

public class Cat extends Animal {
    public Cat(String name, int size) {
        super(name, size);
    }

    @Override
    public void Sound() {
        System.out.println("Meow");
    }
}
