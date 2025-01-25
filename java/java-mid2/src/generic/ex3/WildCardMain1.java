package generic.ex3;

import generic.ex2.Animal;
import generic.ex2.Cat;
import generic.ex2.Dog;

public class WildCardMain1 {
    public static void main(String[] args) {
        Box<Object> objBox = new Box<>();
        Box<Animal> animalBox = new Box<>();
        Box<Dog> dogBox = new Box<>();
        Box<Cat> catBox = new Box<>();

        dogBox.set(new Dog("Bow", 100));
        catBox.set(new Cat("Chally", 30));

        WildCard.<Dog>printGenericV1(dogBox);
        WildCard.<Dog>printGenericV2(dogBox);
        Dog dog = WildCard.<Dog>printAndReturn(dogBox);

        WildCard.printWildCardV2(catBox);

        writeBox(objBox);
        writeBox(animalBox);
        System.out.println(objBox.get());
        System.out.println(animalBox.get().getName());
    }

    static void writeBox(Box<? super Animal> box) {
        box.set(new Dog("Hoq", 123));
    }
}
