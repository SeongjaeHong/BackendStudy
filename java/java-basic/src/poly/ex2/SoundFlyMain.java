package poly.ex2;

public class SoundFlyMain {
    public static void main(String[] args) {
        Chicken chicken = new Chicken();
        Bird bird = new Bird();
        Dog dog = new Dog();

        fly(bird);
        fly(chicken);

        sound(dog);
        sound(bird);
        sound(chicken);

        dog.move();
        bird.move();
    }

    private static void sound(AbstractAnimal animal) {
        animal.sound();
    }

    private static void fly(Fly fly) {
        fly.fly();
    }
}
