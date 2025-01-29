package collection.set.ex;

import java.util.HashSet;
import java.util.Set;

public class ExRectangle {
    public static void main(String[] args) {
        Set<Rectangle> recSet = new HashSet<>();
        recSet.add(new Rectangle(10, 10));
        recSet.add(new Rectangle(10, 10));
        recSet.add(new Rectangle(20, 10));

        for(Rectangle rec: recSet) {
            System.out.println(rec);
        }
    }
}
