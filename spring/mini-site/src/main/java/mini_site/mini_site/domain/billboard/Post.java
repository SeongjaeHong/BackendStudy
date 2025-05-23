package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mini_site.mini_site.domain.member.Member;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 보호
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billboard_id")
    private Billboard billboard;

    @Lob
    private String content; // TODO: 타입 고민. 글, 그림 등을 포함할 수 있어야 함.

    private Integer vote = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Map<Long, Comment> comments = new LinkedHashMap<>();  // 댓글

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    @Builder
    public Post(
            String title,
            Member member,
            Billboard billboard,
            String content
    ) {
        this.title = title;
        this.member = member;
        this.billboard = billboard;
        this.content = content;
    }

    public void addComment(Comment comment) {
        comments.put(comment.getId(), comment);
    }

    public void deleteComment(Long commentId) {
        comments.remove(commentId);
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments.values());
    }

    public void upVote() {
        vote += 1;
    }

    public void downVote() {
        vote -= 1;
    }
}
