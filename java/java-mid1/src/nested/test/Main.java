package nested.test;

public class Main {
    public static void main(String[] args) {
        Library library = new Library(4);
        library.addBook("book1", "author1");
        library.addBook("book21", "author12");
        library.addBook("book31", "author13");
        library.addBook("book41", "author14");
        library.addBook("book41412", "author11234");
        library.showBooks();
    }
}
