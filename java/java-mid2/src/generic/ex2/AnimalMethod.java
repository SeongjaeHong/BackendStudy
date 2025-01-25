package generic.ex2;

public class AnimalMethod {
    public static <T extends Animal> void checkup(T t) {
        System.out.println("Name: " + t.getName());
        System.out.println("Size: " + t.getSize());
        t.Sound();
    }

    public static <T extends Animal, T2 extends Animal> Animal bigger(T t1, T2 t2) {
        return t1.getSize() > t2.getSize()? t1: t2;
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Chalse", 50);
        Dog dog2 = new Dog("Berry", 100);
        Dog cat = new Dog("Ketty", 30);

        AnimalMethod.<Dog>checkup(dog);
        System.out.println("Name: " + AnimalMethod.bigger(dog, dog2).getName());
        System.out.println("Name: " + AnimalMethod.bigger(dog, cat).getName());
    }
}
