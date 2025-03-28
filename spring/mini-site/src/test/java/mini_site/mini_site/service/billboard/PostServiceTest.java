package mini_site.mini_site.service.billboard;

import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.billboard.Post;
import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.repository.billboard.BillboardRepository;
import mini_site.mini_site.repository.billboard.PostRepository;
import mini_site.mini_site.repository.member.MemberRepository;
import mini_site.mini_site.service.billboard.dto.request.PostRequest;
import mini_site.mini_site.service.billboard.dto.response.PostResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired PostService postService;
    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired BillboardRepository billboardRepository;

    @Test
    void writePost() {
        // given
        Member member = new Member("name", "pwd");
        memberRepository.save(member);

        Billboard billboard = new Billboard("name");
        billboardRepository.save(billboard);

        PostRequest postRequest = new PostRequest("post-1", member.getId(), billboard.getId(), "content");

        // when
        Long postId = postService.writePost(postRequest);

        // then
        Post foundPost = postService.findPostById(postId);
        assertEquals(postId, foundPost.getId());
    }

    @Test
    void deletePost() {
        // given
        Member member = new Member("name", "pwd");
        memberRepository.save(member);

        Billboard billboard = new Billboard("name");
        billboardRepository.save(billboard);

        PostRequest postRequest = new PostRequest("post-1", member.getId(), billboard.getId(), "content");

        // when
        Long postId = postService.writePost(postRequest);
        postService.deletePostById(postId);

        // then
        assertFalse(postRepository.existsById(postId));
    }
}