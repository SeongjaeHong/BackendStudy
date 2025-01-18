package static2.ex;

public class MathArrayUtils {
    private MathArrayUtils() {
        // Block create an instance.
    }

    public static int sum(int[] arr) {
        int sum = 0;
        for (int num: arr) {
            sum += num;
        }
        return sum;
    }

    public static double average(int[] arr) {
        return (double) sum(arr) / arr.length;
    }

    public static int min(int[] arr) {
        int min=arr[0];
        for (int i=1; i<arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    public static int max(int[] arr) {
        int max=arr[0];
        for (int i=1; i<arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }
}
