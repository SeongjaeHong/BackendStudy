package string;

public class LoopStringBuilder {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        for(int i=0; i<100000; i++) {
            result.append("Hello Java ");
        }
        long endTime = System.currentTimeMillis();

        System.out.printf("time: %.2f", (endTime - startTime) / 1000.);
    }
}
