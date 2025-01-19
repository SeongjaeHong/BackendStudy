package Abstract1;

public class AnimalMain {
    public static void main(String[] args) {
        Sound(new Dog());
        Move(new Dog());

        Sound(new Cow());
        Move(new Cow());

        Cow cow = new Cow();
        System.out.println(cow.kind);
    }

    public static void Sound(InterfaceAnimal animal) {
        animal.Sound();
    }

    public static void Move(InterfaceAnimal animal) {
        animal.Move();
    }
}
