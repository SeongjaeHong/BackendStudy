package mini_site.mini_site.service.billboard;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.billboard.Post;
import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.exception.PostException;
import mini_site.mini_site.repository.billboard.PostRepository;
import mini_site.mini_site.service.billboard.dto.request.PostRequest;
import mini_site.mini_site.service.billboard.dto.response.PostResponse;
import mini_site.mini_site.service.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final BillboardService billboardService;

    // 글 작성
    @Transactional
    public ResponseEntity<PostResponse> writePost(PostRequest postRequest) {
        Post post = getEntity(postRequest);
        postRepository.save(post);
        return ResponseEntity.ok(
                new PostResponse(
                        post.getId(),
                        post.getMember().getId(),
                        post.getBillboard().getId(),
                        post.getContent()
                )
        );
    }

    // 글 삭제
    @Transactional
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostException("요청한 게시글을 찾을 수 없습니다."));
    }

    private Post getEntity(PostRequest postRequest) {
        Member member = memberService.findMemberById(postRequest.memberId());
        Billboard billboard = billboardService.findBillboardById(postRequest.billboardId());
        return new Post(member, billboard, postRequest.content());
    }
}
