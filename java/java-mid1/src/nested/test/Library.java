package nested.test;

import java.util.Arrays;

public class Library {
    private class Book {
        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }
    }

    private Book[] books;
    private int cur = 0;

    public Library(int numberBooks) {
        books = new Book[numberBooks];
    }

    public void showBooks() {
        for (Book book: books) {
            System.out.printf("Title: %s, author: %s\n", book.getTitle(), book.getAuthor());
        }
    }

    public void addBook(String title, String author) {
       if (cur < books.length) {
           books[cur++] = new Book(title, author);
       }
    }
}