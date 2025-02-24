package jpashop_re_group.jpashop_re.domain.item;

import jakarta.persistence.Entity;
import jpashop_re_group.jpashop_re.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Book extends Product {

    private String author;
    private String isbn;
}
