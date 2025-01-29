package collection.set.ex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ex2 {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(List.of(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(List.of(3, 4, 5, 6, 7));

        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("union = " + union);

        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("intersection = " + intersection);

        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("difference = " + difference);
    }
}
