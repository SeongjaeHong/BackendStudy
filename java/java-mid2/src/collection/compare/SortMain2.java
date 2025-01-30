package collection.compare;

import java.util.*;

public class SortMain2 {
    public static void main(String[] args) {
        Integer[] arr = {3, 2, 1};
        System.out.println(Arrays.toString(arr));

        System.out.println("Ascending order");
        Arrays.sort(arr, new AscComparator());
        System.out.println(Arrays.toString(arr));

        System.out.println("Descending order");
        Arrays.sort(arr, new DscComparator());
        System.out.println(Arrays.toString(arr));
    }

    static class DscComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            System.out.println("o1 = " + o1 + ", o2 = " + o2);
            if (o1 < o2) {
                return 1;
            } else if (o1.equals(o2)) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    static class AscComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            System.out.println("o1 = " + o1 + ", o2 = " + o2);
            if (o1 < o2) {
                return -1;
            } else if (o1.equals(o2)) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
