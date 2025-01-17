package construct1;

public class MemberDefaultMain {
    public static void main(String[] args) {
        MemberConstruct member1 = new MemberConstruct("peter", 29, 97);
        MemberConstruct member2 = new MemberConstruct("evan", 28);

        MemberConstruct[] members = {member1, member2};
        for (MemberConstruct member: members) {
            System.out.println(member.name + " " + member.age + " " + member.grade);
        }
    }
}
