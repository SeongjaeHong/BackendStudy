package collection.deque.test;

import java.util.ArrayDeque;
import java.util.Deque;

public class TaskScheduler {
    Deque<Task> tasks = new ArrayDeque<>();

    public int getRemainingTasks() {
        return tasks.size();
    }

    public void processNextTask() {
        if (!tasks.isEmpty()) {
            tasks.poll().execute();
        }
    }

    public void addTask(Task task) {
        tasks.offer(task);
    }
}
