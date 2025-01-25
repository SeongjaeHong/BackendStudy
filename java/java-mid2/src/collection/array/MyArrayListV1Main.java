package collection.array;

public class MyArrayListV1Main {
    public static void main(String[] args) {
        MyArrayList1 list = new MyArrayList1();
        System.out.println(list);
        list.add("b");
        System.out.println(list);
        list.add(3);
        System.out.println(list);
        list.add("hello");
        list.add(38);

        System.out.println("Use functions");
        System.out.println("list.size() = " + list.size());
        System.out.println("list.get(1) = " + list.get(1));
        System.out.println("list.indexOf(\"hello\") = " + list.indexOf("hello"));
        System.out.println("list.set(1,\"Great\") = " + list.set(1, "Great"));
        System.out.println(list);

        for (int i=0; i<30; i++) {
            list.add(i);
        }

        System.out.println(list);
        list.insert("Insert me", 6);
        System.out.println(list);
        System.out.println("list.remove(3) = " + list.remove(3));
        System.out.println("list.remove(33) = " + list.remove(33));
        System.out.println(list);
    }
}
