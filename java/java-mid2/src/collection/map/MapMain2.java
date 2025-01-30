package collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapMain2 {
    public static void main(String[] args) {
        Map<String, Integer> studentMap = new HashMap<>();
        studentMap.put("A", 90);

        if (!studentMap.containsKey("A")) {
            studentMap.put("A", 123);
        }

        studentMap.putIfAbsent("A", 500);
        System.out.println(studentMap);
    }
}
