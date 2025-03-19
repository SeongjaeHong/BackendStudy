package mini_site.mini_site.domain.billboard;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Billboard {
    @Id @GeneratedValue
    @Column(name = "billboard_id")
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "billboard", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
