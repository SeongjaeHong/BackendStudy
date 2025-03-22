package mini_site.mini_site.repository.billboard;

import mini_site.mini_site.domain.billboard.Billboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillboardRepository extends JpaRepository<Billboard, Long> {
}
