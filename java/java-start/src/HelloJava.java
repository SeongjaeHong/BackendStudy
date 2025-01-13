import java.util.Scanner;

public class HelloJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input a number of numbers to input: ");
        int num = scanner.nextInt();
        int [] numbers = new int[num];

        System.out.printf("Input %d numbers.\n", num);
        for(int i=0; i<num; i++){
            System.out.print("Number: ");
            numbers[i] = scanner.nextInt();
        }

        int min, max;
        min = max = numbers[0];

        for(int x: numbers){
            if(min > x) {
                min = x;
            } else if (max < x) {
                max = x;
            }
        }

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);

    }
}
