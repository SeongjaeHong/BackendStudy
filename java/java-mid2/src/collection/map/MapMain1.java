package collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMain1 {
    public static void main(String[] args) {
        Map<String, Integer> studentMap = new HashMap<>();
        studentMap.put("A", 90);
        studentMap.put("B", 90);
        studentMap.put("C", 90);
        studentMap.put("D", 100);
        System.out.println(studentMap);

        System.out.println("studentMap.get(\"C\") = " + studentMap.get("C"));

        System.out.println("studentMap.keySet() = " + studentMap.keySet());
        System.out.println("studentMap.values() = " + studentMap.values());

        Set<Map.Entry<String, Integer>> entries = studentMap.entrySet();
        for (Map.Entry<String, Integer> entry: entries) {
            String key = entry.getKey();
            Integer val = entry.getValue();
            System.out.println("Key="+key+", value="+val);
        }
    }
}
