package collection.set;

import collection.set.member.Member;

public class MyHashSetV2Main {
    public static void main(String[] args) {
        MyHashSetV2 set = new MyHashSetV2(10);
        Member hi = new Member("hi");
        Member jpa = new Member("JPA");
        Member java = new Member("JAVA");
        Member spring = new Member("SPRING");

        System.out.println("hi.hashCode() = " + hi.hashCode());
        System.out.println("jpa.hashCode() = " + jpa.hashCode());
        System.out.println("java.hashCode() = " + java.hashCode());
        System.out.println("spring.hashCode() = " + spring.hashCode());

        set.add(hi);
        set.add(jpa);
        set.add(java);
        set.add(spring);
        System.out.println(set);

        Member searchValue = new Member("JPA");
        System.out.println("set.contains(" + searchValue.getId() + ") = " + set.contains(searchValue));
    }
}
