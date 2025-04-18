package mini_site.mini_site.service.billboard;

import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.domain.member.MemberLevel;
import mini_site.mini_site.exception.BillboardException;
import mini_site.mini_site.repository.billboard.BillboardRepository;
import mini_site.mini_site.repository.member.MemberRepository;
import mini_site.mini_site.service.billboard.dto.request.PostRequest;
import mini_site.mini_site.service.billboard.dto.response.PostResponse;
import mini_site.mini_site.service.member.dto.response.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BillboardServiceTest {
    @Autowired BillboardService billboardService;
    @Autowired PostService postService;

    @Autowired BillboardRepository billboardRepository;
    @Autowired MemberRepository memberRepository;

    @DisplayName("게시판을 등록한다.")
    @Test
    void registerBillboard() {
        // given
        MemberResponse memberResponse = new MemberResponse(1L, "admin", MemberLevel.ADMIN);

        // when
        Billboard billboard = billboardService.registerBillboard("test", memberResponse);

        // then
        assertEquals("test", billboard.getName());
    }

    @DisplayName("게시판을 조회한다.")
    @Test
    void findBillboardById() {
        // given
        MemberResponse memberResponse = new MemberResponse(1L, "admin", MemberLevel.ADMIN);
        Billboard billboard = billboardService.registerBillboard("test", memberResponse);

        // when
        Billboard foundBillboard = billboardService.findBillboardById(billboard.getId());

        // then
        assertEquals(billboard, foundBillboard);
        assertThrows(BillboardException.class, () -> billboardService.findBillboardById(-1L));
    }
    @DisplayName("게시글을 조회한다.")
    @Test
    void findPostsByBillboardId() {
        // given
        Member member = new Member("id", "user-1", "pwd");
        memberRepository.save(member);

        Billboard billboard = new Billboard("board-1");
        billboardRepository.save(billboard);

        Long postId1 = postService.writePost(new PostRequest("post-1", member.getId(), billboard.getId(), "content-1"));
        Long postId2 = postService.writePost(new PostRequest("post-2", member.getId(), billboard.getId(), "content-2"));

        // when
        List<PostResponse> posts = billboardService.findPostsByBoardId(billboard.getId());

        // then
        assertEquals(2, posts.size());
        assertAll(
                () -> assertEquals(postId1, posts.get(0).postId()),
                () -> assertEquals(postId2, posts.get(1).postId())
        );
    }
}