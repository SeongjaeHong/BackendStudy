package enumeration.test;

public enum AuthGrade {
    GUEST(1, "Customer"),
    LOGIN(2, "Member"),
    ADMIN(3, "Admin");

    private final int grade;
    private final String userType;

    AuthGrade(int grade, String userType) {
        this.grade = grade;
        this.userType = userType;
    }

    public int getGrade() {
        return grade;
    }

    public String getUserType() {
        return userType;
    }

    public void description() {
        System.out.println("level=" + grade);
        System.out.println("description=" + userType);
    }

    public void menu() {
        System.out.println("===Menu list===");
        System.out.println("- Main page");
        if (this.grade >= LOGIN.grade) {
            System.out.println("- Email management page");
        }
        if (this.grade >= ADMIN.grade) {
            System.out.println("- Admin page");
        }
    }

    public static AuthGrade getGrade(String grade) {
        AuthGrade authGrade = null;
        try {
            authGrade = AuthGrade.valueOf(grade);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid grade: " + grade);
            System.exit(0);
        }

        return authGrade;
    }
}
