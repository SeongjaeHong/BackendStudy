package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import mini_site.mini_site.domain.member.Member;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billboard_id", referencedColumnName = "billboard_id")
    private Billboard billboard;

    private String content; // TODO: 타입 고민. 글, 그림 등을 포함할 수 있어야 함.

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    @Builder
    public Post(
            Member member,
            Billboard billboard,
            String content
    ) {
        this.member = member;
        this.billboard = billboard;
        this.content = content;

        billboard.addPost(this);
    }
}
