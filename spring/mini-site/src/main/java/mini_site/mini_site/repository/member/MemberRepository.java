package mini_site.mini_site.repository.member;

import mini_site.mini_site.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
