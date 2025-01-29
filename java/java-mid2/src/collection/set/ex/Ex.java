package collection.set.ex;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Ex {
    public static void main(String[] args) {
        Integer[] arr = {1,3,2,1,2,3,4};
        Set<Integer> linkset = new LinkedHashSet<>(List.of(arr));
        Set<Integer> treeset = new TreeSet<>(List.of(arr));
        System.out.println(linkset);
        System.out.println(treeset);
    }
}
