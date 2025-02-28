package jpashop_re_group.jpashop_re.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jpashop_re_group.jpashop_re.controller.BookForm;
import jpashop_re_group.jpashop_re.exception.InvalidUserException;
import jpashop_re_group.jpashop_re.exception.QuantityLessThanZeroException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public abstract class Product {

    @Id @GeneratedValue
    private Long productId;

    @NotEmpty
    @Setter
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    @Setter
    private Long price;

    @Setter
    private int quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Suborder> suborders = new ArrayList<>();

    public boolean changePrice(Long newPrice, Long sellerId) throws InvalidUserException {
        if (!sellerId.equals(this.seller.getSellerId())) {
            throw new InvalidUserException("물건 판매자가 아닙니다.");
        }
        this.price = newPrice;
        return true;
    }

    public boolean addQuantity(int value) {
        quantity += value;
        return true;
    }

    public boolean subtractQuantity(int value) throws QuantityLessThanZeroException {
        int tempQuantity = quantity - value;
        if (tempQuantity < 0) {
            throw new QuantityLessThanZeroException();
        }
        quantity -= value;
        return true;
    }

    public void setAttributes(BookForm form) {
        name = form.getName();
        price = form.getPrice();
        quantity = form.getStockQuantity();
    }
}
