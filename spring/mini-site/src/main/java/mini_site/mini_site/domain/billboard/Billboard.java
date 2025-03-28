package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Billboard {
    @Id @GeneratedValue
    @Column(name = "billboard_id")
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "billboard", cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();

    public Billboard(String name) {
        this.name = name;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void deletePost(Post post) {
        posts.remove(post);
    }
}
