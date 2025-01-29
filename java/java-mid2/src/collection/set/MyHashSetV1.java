package collection.set;

import java.util.Arrays;
import java.util.LinkedList;

public class MyHashSetV1 {
    private static final int DEFAULT_CAPACITY = 16;

    LinkedList<Integer>[] buckets;

    private int size = 0;
    private int capacity = DEFAULT_CAPACITY;

    public MyHashSetV1() {
        initBucket(DEFAULT_CAPACITY);
    }

    public MyHashSetV1(int capacity) {
        this.capacity = capacity;
        initBucket(capacity);
    }

    private void initBucket(int capacity) {
        buckets = new LinkedList[capacity];
        for (int i=0; i<capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hashIndex(int value) {
        return value % capacity;
    }

    public boolean add(int value) {
        int hashIndex = hashIndex(value);
        LinkedList<Integer> bucket = buckets[hashIndex];
        if (bucket.contains(value)) {
            return false;
        }
        bucket.add(value);
        size++;
        return true;
    }

    public boolean contains(int value) {
        int hashIndex = hashIndex(value);
        LinkedList<Integer> bucket = buckets[hashIndex];
        return bucket.contains(value);
    }

    public boolean remove(int value) {
        int hashIndex = hashIndex(value);
        LinkedList<Integer> bucket = buckets[hashIndex];
        boolean result = bucket.remove(Integer.valueOf(value));  // new Integer(value)
        if (result) {
            size--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "MyHashSetV1{" +
                "buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}
