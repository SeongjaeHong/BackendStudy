package jpashop_re_group.jpashop_re.controller;

import jpashop_re_group.jpashop_re.domain.item.Book;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id;

    private String name;
    private Long price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public void setAttribute(Book book) {
        id = book.getProductId();
        name = book.getName();
        price = book.getPrice();
        stockQuantity = book.getQuantity();
        author = book.getAuthor();
        isbn = book.getIsbn();
    }
}
