package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpabook.jpashop.controller.BookForm;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B")
@Getter
public class Book extends Item {

    private String author;
    private String isbn;

    public Book() {}

    public Book(BookForm bookForm) {
        this.setName(bookForm.getName());
        this.setPrice(bookForm.getPrice());
        this.setStockQuantity(bookForm.getStockQuantity());
        this.author = bookForm.getAuthor();
        this.isbn = bookForm.getIsbn();
    }
}
