package collection.set.javaset;

import java.util.*;

public class JavaSetMain {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();

        run(hashSet);
        run(linkedSet);
        run(treeSet);
    }

    private static void run(Set<String> set) {
        System.out.println("set = " + set.getClass());
        set.add("C");
        set.add("B");
        set.add("A");
        set.add("2");
        set.add("F");
        set.add("1");
        set.add("3");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

    }
}
