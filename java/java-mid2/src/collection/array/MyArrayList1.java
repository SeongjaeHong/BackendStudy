package collection.array;

import java.util.Arrays;

public class MyArrayList1 {
    private static final int DEFAULT_CAPACITY = 5;

    private Object[] elementData;
    private int size = 0;

    public MyArrayList1() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList1(int initalCapacity) {
        elementData = new Object[initalCapacity];
    }

    public int size() {
        return size;
    }

    public void add(Object e) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = e;
    }

    public void insert(Object e, int index) {
        if (size == elementData.length) {
            grow();
        }
        shiftRightFrom(index);
        elementData[index] = e;
        size += 1;
    }

    private void shiftRightFrom(int index) {
        for (int i=size; i >= index; i--) {
            elementData[i+1] = elementData[i];
        }
    }

    private void shiftLeftFrom(int index) {
        for (int i=index; i < size; i++) {
            elementData[i] = elementData[i+1];
        }
    }

    public Object remove(int index) {
        Object oldValue = get(index);
        shiftLeftFrom(index);
        size -= 1;
        return oldValue;
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity * 2;
        elementData = Arrays.copyOf(elementData, newCapacity);

        System.out.println("Grow from " + oldCapacity + "to " + newCapacity);
    }

    public Object get(int index) {
        return elementData[index];
    }

    public Object set(int index, Object element) {
        Object oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    public int indexOf(Object o) {
        for (int i=0; i < size; i++) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size)) +
        " size=" + size + ", capacity=" + elementData.length;
    }
}
