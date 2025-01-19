package poly.ex;

public class AnimalSound {
    public void Sound() {
        System.out.println("Animal sound");
    }
    public static void main(String[] args) {
        AnimalSound dog = new Dog();
        AnimalSound cow = new Cow();

        AnimalSound[] animals = {dog, cow};
        for (AnimalSound animal: animals) {
            animal.Sound();
        }

    }
}
