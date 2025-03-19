package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import mini_site.mini_site.domain.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billboard_id", referencedColumnName = "billboard_id")
    private Billboard billboard;

    private String content; // TODO: 타입 고민. 글, 그림 등을 포함할 수 있어야 함.

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;
}
