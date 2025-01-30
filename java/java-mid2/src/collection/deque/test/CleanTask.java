package collection.deque.test;

public class CleanTask implements Task {
    @Override
    public void execute() {
        System.out.println("Clean up unused resource.");
    }
}
