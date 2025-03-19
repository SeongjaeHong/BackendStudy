package mini_site.mini_site.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import mini_site.mini_site.domain.billboard.Comment;
import mini_site.mini_site.domain.billboard.Post;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
