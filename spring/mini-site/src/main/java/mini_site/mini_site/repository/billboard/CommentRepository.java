package mini_site.mini_site.repository.billboard;

import mini_site.mini_site.domain.billboard.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
