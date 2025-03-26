package mini_site.mini_site.repository.billboard;

import mini_site.mini_site.domain.billboard.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
