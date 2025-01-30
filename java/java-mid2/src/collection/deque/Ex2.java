package collection.deque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ex2 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 500);
        map.put("Banana", 1000);
        map.put("Mango", 1000);
        map.put("Kiwi", 750);

        List<String> arr = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1000) {
                arr.add(entry.getKey());
            }
        }

        System.out.println(arr);
    }
}
