package jpashop_re_group.jpashop_re.domain;

import jakarta.persistence.*;
import jpashop_re_group.jpashop_re.exception.InvalidQuantityException;
import jpashop_re_group.jpashop_re.exception.QuantityLessThanZeroException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Suborder {

    @Id @GeneratedValue
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @Setter @Getter
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @Setter @Getter
    private Product product;

    @Getter
    private int quantity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    public void setQuantity(int quantity) throws InvalidQuantityException, QuantityLessThanZeroException {
        if (quantity > product.getQuantity()) {
            this.quantity = 0;
            throw new InvalidQuantityException();
        }
        product.subtractQuantity(quantity);
        this.quantity = quantity;
    }
}
