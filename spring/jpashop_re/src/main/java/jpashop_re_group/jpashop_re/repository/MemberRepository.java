package jpashop_re_group.jpashop_re.repository;

import jpashop_re_group.jpashop_re.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    @Transactional
    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findall() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }
}
