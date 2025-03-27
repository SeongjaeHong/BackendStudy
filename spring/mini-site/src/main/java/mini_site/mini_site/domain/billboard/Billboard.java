package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Billboard {
    @Id @GeneratedValue
    @Column(name = "billboard_id")
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "billboard", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public Billboard(String name) {
        this.name = name;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
