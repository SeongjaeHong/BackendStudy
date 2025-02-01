package spring_basic.core.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
