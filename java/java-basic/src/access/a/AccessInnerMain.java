package access.a;

public class AccessInnerMain {
    public static void main(String[] args) {
        AccessData data = new AccessData();
        data.publicMethod();
        data.defaultMethod();
        data.innerAccess();
    }
}
