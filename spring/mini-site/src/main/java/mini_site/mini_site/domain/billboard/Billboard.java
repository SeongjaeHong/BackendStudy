package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
public class Billboard {
    @Id @GeneratedValue
    @Column(name = "billboard_id")
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "billboard", cascade = CascadeType.ALL)
    private Map<Long, Post> posts = new LinkedHashMap<>();

    public Billboard(String name) {
        this.name = name;
    }

    public void addPost(Post post) {
        posts.put(post.getId(), post);
    }

    public void deletePost(Post post) {
        posts.remove(post.getId());
    }

    public List<Post> getPosts() {
        return new ArrayList<>(posts.values());
    }
}
