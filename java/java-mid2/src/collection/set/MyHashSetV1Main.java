package collection.set;

public class MyHashSetV1Main {
    public static void main(String[] args) {
        MyHashSetV1 set = new MyHashSetV1();
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(5);
        set.add(6);
        set.add(15);
        set.add(199);
        set.add(1999);
        System.out.println(set);
        System.out.println("set.contains(15) = " + set.contains(15));
        set.remove(15);
        System.out.println(set);
    }
}
