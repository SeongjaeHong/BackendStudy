package collection.deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeMain {
    public static void main(String[] args) {
        Deque<Integer> dequeStack = new ArrayDeque<>();
        Deque<Integer> dequeQueue = new ArrayDeque<>();

        dequeStack.push(1);
        dequeStack.push(2);
        dequeStack.push(3);

        dequeQueue.offer(1);
        dequeQueue.offer(2);
        dequeQueue.offer(3);

        System.out.println("dequeStack = " + dequeStack);
        System.out.println("dequeQueue = " + dequeQueue);

        System.out.println("dequeStack.pop() = " + dequeStack.pop());
        System.out.println("dequeQueue.poll() = " + dequeQueue.poll());
    }
}
