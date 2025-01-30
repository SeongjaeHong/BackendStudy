package collection.compare;

import java.util.Arrays;

public class SortMain1 {
    public static void main(String[] args) {
        Integer[] arr = {3, 2, 1};
        System.out.println(Arrays.toString(arr));

        System.out.println("Basic sorting");
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
