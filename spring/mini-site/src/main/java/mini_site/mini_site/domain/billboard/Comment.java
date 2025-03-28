package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import mini_site.mini_site.domain.member.Member;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;   // TODO: 타입 고민. 글, 그림 등을 포함할 수 있어야 함.

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;
}
