package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mini_site.mini_site.domain.member.Member;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)  // 기본 생성자 보호
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Lob
    private String content;   // TODO: 타입 고민. 글, 그림 등을 포함할 수 있어야 함.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;  // 부모 댓글. (null이면 최상위 댓글)

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.PERSIST)
    private List<Comment> childComments = new ArrayList<>(); // 대댓글 목록

    /**
     * isVestige is set to true when it is deleted but has child comments.
     * This is to keep child comments alive even after their parent comment has been deleted.
     * When this comment is called, it will be shown as a delete message.
     */
    private boolean isVestige;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    public Comment(Member member, Post post, String content, Comment parentComment) {
        this.member = member;
        this.post = post;
        this.content = content;
        this.parentComment = parentComment;
    }

    public void addChildComment(Comment child) {
        child.parentComment = this;
        childComments.add(child);
    }

    public void deleteChildComment(Comment child) {
        childComments.remove(child);
    }

    public void setAsDeleted() {
        this.isVestige = true;
        this.content = "[삭제된 댓글입니다]";
    }
}
