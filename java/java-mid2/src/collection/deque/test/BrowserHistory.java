package collection.deque.test;

import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserHistory {
    private Deque<String> history = new ArrayDeque<>();
    private String currentPage = null;

    public void visitPage(String url) {
        System.out.println("Visit: " + url);
        history.push(url);
    }

    public String goBack() {
        history.pop();
        currentPage = history.peek();
        System.out.println("Go back: " + currentPage);
        return currentPage;
    }
}
