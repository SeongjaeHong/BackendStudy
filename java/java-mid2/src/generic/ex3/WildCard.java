package generic.ex3;

import generic.ex2.Animal;

public class WildCard {
    static <T> void printGenericV1(Box<T> box) {
        System.out.println("T = " + box.get());
    }

    static <T extends Animal> void printGenericV2(Box<T> box) {
        T t = box.get();
        System.out.println("Name = " + t.getName());
    }

    static <T extends Animal> T printAndReturn(Box<T> box) {
        T t = box.get();
        System.out.println("Name = " + t.getName());
        return t;
    }

    static Animal printAndReturnV2(Box<? extends Animal> box) {
        Animal t = box.get();
        System.out.println("Name = " + t.getName());
        return t;
    }

    static void printWildCardV2(Box<? extends Animal> box) {
        Animal animal = box.get();
        System.out.println("Name = " + animal.getName());
    }
}
