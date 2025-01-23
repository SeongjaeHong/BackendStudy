package generic.ex1;

public class GenericBox<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public static void main(String[] args) {
        GenericBox<Integer> intBox = new GenericBox<>();
        intBox.set(5);
        System.out.println(intBox.get());

        GenericBox<String> strBox = new GenericBox<>();
        strBox.set("WOW");
        System.out.println(strBox.get());
    }
}
