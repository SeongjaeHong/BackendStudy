package collection.deque;

import java.util.HashMap;
import java.util.Map;

public class Ex1 {
    public static void main(String[] args) {
        String text = "orange banana apple apple banana apple";

        Map<String, Integer> map = new HashMap<>();
        for (String word: text.split(" ")) {
            /*if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }*/
            int count = map.getOrDefault(word, 0);
            map.put(word, count + 1);
        }
        System.out.println("map = " + map);
    }
}
