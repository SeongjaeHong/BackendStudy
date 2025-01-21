package enumeration;

public class Main {
    public static void main(String[] args) {
        test(Grade.GOLD);
        test2(GradeEnum.GOLD);

        GradeEnum[] ge = GradeEnum.values();
        for(GradeEnum e: ge) {
            System.out.println("Name: " + e.name());
            System.out.println("Discount Percentage: " + e.getDiscountPercentage());
        }
    }

    public static void test2(GradeEnum grade) {
        if (grade == GradeEnum.BASIC) {
            System.out.println("Basic");
        } else if (grade == GradeEnum.GOLD) {
            System.out.println("GOLD");
        } else {
            System.out.println("NO");
        }
    }

    public static void test(Grade grade) {
        if (grade == Grade.BASIC) {
            System.out.println("Basic");
        } else if (grade == Grade.GOLD) {
            System.out.println("GOLD");
        } else {
            System.out.println("NO");
        }
    }
}
