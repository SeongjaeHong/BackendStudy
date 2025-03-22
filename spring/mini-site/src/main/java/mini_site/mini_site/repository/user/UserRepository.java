package mini_site.mini_site.repository.user;

import mini_site.mini_site.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
