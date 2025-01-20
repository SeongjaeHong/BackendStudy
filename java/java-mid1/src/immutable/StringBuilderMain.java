package immutable;

public class StringBuilderMain {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = sb;
        sb.append('A');
        sb.append('B');
        System.out.println("sb2 : " + sb2);
        sb.append("-Wow-");
        sb.insert(2, "#");
        System.out.println(sb);
        System.out.println("sb2 : " + sb2);

    }
}
