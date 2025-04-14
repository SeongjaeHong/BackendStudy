package mini_site.mini_site.domain.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import mini_site.mini_site.domain.billboard.Comment;
import mini_site.mini_site.domain.billboard.Post;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotBlank
    private String loginId;

    @NotBlank
    @Setter
    private String password;

    @NotBlank
    @Setter
    private String name;

    @Setter
    private MemberLevel memberLevel = MemberLevel.MEMBER;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Map<Long, Post> posts = new LinkedHashMap<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Map<Long, Comment> comments = new LinkedHashMap<>();

    @Builder
    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void addPost(Post post) {
        posts.put(post.getId(), post);
    }

    public void deletePost(Post post) {
        posts.remove(post.getId());
    }

    public List<Post> getPost() {
        return new ArrayList<>(posts.values());
    }

    public void addComment(Comment comment) {
        comments.put(comment.getId(), comment);
    }

    public void deleteComment(Comment comment) {
        comments.remove(comment.getId());
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments.values());
    }
}
