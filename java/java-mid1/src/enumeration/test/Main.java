package enumeration.test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.printf("Input your grade%s: ", Arrays.toString(AuthGrade.values()));
        String grade = userInput.nextLine().toUpperCase();

        AuthGrade userGrade = AuthGrade.getGrade(grade);
        userGrade.menu();
    }
}
