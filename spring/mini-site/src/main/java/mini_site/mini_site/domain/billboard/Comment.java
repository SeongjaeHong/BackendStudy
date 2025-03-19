package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import mini_site.mini_site.domain.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "comment_id")
    private Long parentCommentId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCommentId", cascade = CascadeType.ALL)
    private List<Comment> subComments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private String content;   // TODO: 타입 고민. 글, 그림 등을 포함할 수 있어야 함.

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;
}
