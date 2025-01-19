package poly.ex;

public abstract class AnimalSound {

    public abstract void Sound();

    public static void main(String[] args) {
        AnimalSound dog = new Dog();
        AnimalSound cow = new Cow();

        AnimalSound[] animals = {dog, cow};
        for (AnimalSound animal: animals) {
            animal.Sound();
        }

    }
}
