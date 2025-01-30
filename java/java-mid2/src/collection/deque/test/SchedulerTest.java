package collection.deque.test;

public class SchedulerTest {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.addTask(new CompressionTask());
        scheduler.addTask(new BackupTask());
        scheduler.addTask(new CleanTask());

        System.out.println("Start a task");
        run(scheduler);
        System.out.println("End a task");
    }

    private static void run(TaskScheduler scheduler) {
        while (scheduler.getRemainingTasks() > 0) {
            scheduler.processNextTask();
        }
    }
}
