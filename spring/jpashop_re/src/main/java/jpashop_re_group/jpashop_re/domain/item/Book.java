package jpashop_re_group.jpashop_re.domain.item;

import jakarta.persistence.Entity;
import jpashop_re_group.jpashop_re.controller.BookForm;
import jpashop_re_group.jpashop_re.domain.Product;
import lombok.Getter;

@Entity
@Getter
public class Book extends Product {

    private String author;
    private String isbn;

    public void setAttributes(BookForm form) {
        super.setAttributes(form);
        author = form.getAuthor();
        isbn = form.getIsbn();
    }
}
