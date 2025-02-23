package jpashop_re_group.jpashop_re.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
public class Seller {

    @Id @GeneratedValue
    @Column(name = "seller_id")
    private Long sellerId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    @OneToOne(mappedBy = "seller")
    private Member member;
}
