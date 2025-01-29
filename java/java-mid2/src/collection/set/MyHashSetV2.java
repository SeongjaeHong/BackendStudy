package collection.set;

import java.util.Arrays;
import java.util.LinkedList;

public class MyHashSetV2 {
    private static final int DEFAULT_CAPACITY = 16;

    LinkedList<Object>[] buckets;

    private int size = 0;
    private int capacity = DEFAULT_CAPACITY;

    public MyHashSetV2() {
        initBucket(DEFAULT_CAPACITY);
    }

    public MyHashSetV2(int capacity) {
        this.capacity = capacity;
        initBucket(capacity);
    }

    private void initBucket(int capacity) {
        buckets = new LinkedList[capacity];
        for (int i=0; i<capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hashIndex(Object value) {
        return Math.abs(value.hashCode()) % capacity;
    }

    public boolean add(Object value) {
        int hashIndex = hashIndex(value);
        LinkedList<Object> bucket = buckets[hashIndex];
        if (bucket.contains(value)) {
            return false;
        }
        bucket.add(value);
        size++;
        return true;
    }

    public boolean contains(Object value) {
        int hashIndex = hashIndex(value);
        LinkedList<Object> bucket = buckets[hashIndex];
        return bucket.contains(value);
    }

    public boolean remove(Object value) {
        int hashIndex = hashIndex(value);
        LinkedList<Object> bucket = buckets[hashIndex];
        boolean result = bucket.remove(value);
        if (result) {
            size--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "MyHashSetV2{" +
                "buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}
