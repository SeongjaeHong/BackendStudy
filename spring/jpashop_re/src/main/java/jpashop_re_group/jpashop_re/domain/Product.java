package jpashop_re_group.jpashop_re.domain;

import jpashop_re_group.jpashop_re.exception.InvalidUserException;
import jpashop_re_group.jpashop_re.exception.QuantityLessThanZeroException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Product {

    @Id @GeneratedValue
    private Long productId;
    private String name;

    @Setter
    private Long sellerId;

    @Setter
    private int price;

    @Setter
    private int quantity;

    public boolean changePrice(int newPrice, Long sellerId) throws InvalidUserException {
        if (!sellerId.equals(this.sellerId)) {
            throw new InvalidUserException("물건 판매자가 아닙니다.");
        }
        this.price = newPrice;
        return true;
    }

    public boolean changeQuantity(int changeValue) throws QuantityLessThanZeroException {
        int tempQuantity = quantity + changeValue;
        if (tempQuantity < 0) {
            throw new QuantityLessThanZeroException();
        }
        quantity += changeValue;
        return true;
    }
}
