package mini_site.mini_site.service.billboard;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.billboard.Comment;
import mini_site.mini_site.domain.billboard.Post;
import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.exception.PostException;
import mini_site.mini_site.repository.billboard.PostRepository;
import mini_site.mini_site.service.billboard.dto.request.PostRequest;
import mini_site.mini_site.service.member.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final BillboardService billboardService;

    // 글 작성
    @Transactional
    public Long writePost(PostRequest postRequest) {
        Post post = getEntity(postRequest);
        postRepository.save(post);

        addPostToEntity(post);

        return post.getId();
    }

    // 글 삭제
    @Transactional
    public void deletePostById(Long postId) {
        Post post = findPostById(postId);

        deletePostFromEntity(post);

        postRepository.deleteById(postId);
    }

    public List<Comment> getComments(Long postId) {
        Post post = findPostById(postId);
        return post.getComments();
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostException("요청한 게시글을 찾을 수 없습니다."));
    }

    private Post getEntity(PostRequest postRequest) {
        Member member = memberService.findMemberById(postRequest.memberId());
        Billboard billboard = billboardService.findBillboardById(postRequest.billboardId());
        return new Post(postRequest.postTitle(), member, billboard, postRequest.content());
    }

    private void addPostToEntity(Post post) {
        // Add in the billboard
        post.getBillboard().addPost(post);

        // Add in the member
        post.getMember().addPost(post);
    }

    private void deletePostFromEntity(Post post) {
        // Delete from the billboard
        post.getBillboard().deletePost(post);

        // Delete from the member
        post.getMember().deletePost(post);
    }
}
