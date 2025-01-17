package construct1;

public class MemberConstruct {
    String name;
    int age;
    int grade;

    MemberConstruct(String name, int age) {
//      Call MemberConstruct(String, int, int) below.
//      Rule 1. It can be called only in a constructor
//      Rule 2. "this()" must be used only on the 1st line.
        this(name, age, 50);

//        This is bothersome.
//        this.name = name;
//        this.age = age;
//        this.grade = 50;
    }

    MemberConstruct(String name, int age, int grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}
