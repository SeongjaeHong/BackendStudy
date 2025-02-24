package jpashop_re_group.jpashop_re.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Orders {

    @Id @GeneratedValue
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Suborder> suborders = new ArrayList<>();

    @Getter
    private Long aggregate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    public void addSubOrder(Suborder suborder) {
        suborders.add(suborder);
    }

    public void setAggregate() {
        Long price = 0L;
        for (Suborder suborder: suborders) {
            price += suborder.getProduct().getPrice();
        }
        this.aggregate = price;
    }
}
