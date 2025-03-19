package mini_site.mini_site.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

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
}
