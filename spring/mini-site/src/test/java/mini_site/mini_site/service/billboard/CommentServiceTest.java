package mini_site.mini_site.service.billboard;

import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.billboard.Comment;
import mini_site.mini_site.domain.billboard.Post;
import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.exception.CommentException;
import mini_site.mini_site.repository.billboard.BillboardRepository;
import mini_site.mini_site.repository.billboard.CommentRepository;
import mini_site.mini_site.repository.billboard.PostRepository;
import mini_site.mini_site.repository.member.MemberRepository;
import mini_site.mini_site.service.billboard.dto.request.CommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {
    @Autowired CommentService commentService;
    @Autowired PostService postService;

    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired BillboardRepository billboardRepository;
    @Autowired CommentRepository commentRepository;

    @DisplayName("댓글을 작성한다.")
    @Test
    void writeComment() {
        // given
        Member member = new Member("member-1", "pwd");
        memberRepository.save(member);

        Billboard billboard = new Billboard("board-1");
        billboardRepository.save(billboard);

        Post post = new Post("post-1", member, billboard, "content");
        postRepository.save(post);

        Post post2 = new Post("post-2", member, billboard, "content");
        postRepository.save(post2);

        // when #1 - Normal case
        CommentRequest commentRequest = new CommentRequest(post.getId(), member.getId(), "comment-1");
        Long commentId = commentService.writeComment(commentRequest);
        // then #1
        assertEquals(1, postService.getComments(post.getId()).size());
        assertEquals("comment-1", commentRepository.findById(commentId).orElseThrow().getContent());
        assertEquals(1, member.getComments().size());
    }

    @DisplayName("댓글을 삭제한다.")
    @Test
    void deleteComment() {
        // given
        Member member = new Member("member-1", "pwd");
        memberRepository.save(member);

        Billboard billboard = new Billboard("board-1");
        billboardRepository.save(billboard);

        Post post = new Post("post-1", member, billboard, "content");
        postRepository.save(post);

        CommentRequest commentRequest = new CommentRequest(post.getId(), member.getId(), "comment-1");
        Long commentId = commentService.writeComment(commentRequest);

        // when
        commentService.deleteComment(commentId);

        // then
        assertEquals(0, postService.getComments(post.getId()).size());
        assertEquals(0, member.getComments().size());
    }

    @DisplayName("대댓글을 작성한다.")
    @Test
    void writeChildComment() {
        // given
        Member member = new Member("member-1", "pwd");
        memberRepository.save(member);

        Member member2 = new Member("member-2", "pwd");
        memberRepository.save(member2);

        Billboard billboard = new Billboard("board-1");
        billboardRepository.save(billboard);

        Post post = new Post("post-1", member, billboard, "content");
        postRepository.save(post);

        // when
        CommentRequest commentRequest = new CommentRequest(post.getId(), member.getId(), "comment-1");
        Long commentId = commentService.writeComment(commentRequest);

        CommentRequest commentRequest2 = new CommentRequest(post.getId(), member2.getId(), "child-1 of comment-1", commentId);
        Long commentId2 = commentService.writeComment(commentRequest2);

        CommentRequest commentRequest3 = new CommentRequest(post.getId(), member.getId(), "child-2 of comment-1", commentId);
        Long commentId3 = commentService.writeComment(commentRequest3);

        CommentRequest commentRequest4 = new CommentRequest(post.getId(), member2.getId(), "child-1 of child-2 of comment-1", commentId3);
        Long commentId4 = commentService.writeComment(commentRequest4);

        // then
        assertEquals(4, postService.getComments(post.getId()).size());
        assertEquals(2, commentRepository.findById(commentId).orElseThrow().getChildComments().size());
        assertEquals(0, commentRepository.findById(commentId2).orElseThrow().getChildComments().size());
        assertEquals(1, commentRepository.findById(commentId3).orElseThrow().getChildComments().size());
        assertEquals(commentId4, postService.getComments(post.getId()).get(3).getId());
        assertEquals(commentId3, postService.getComments(post.getId()).get(3).getParentComment().getId());
        assertEquals(member, commentRepository.findById(commentId).orElseThrow().getMember());
        assertEquals(member2, commentRepository.findById(commentId2).orElseThrow().getMember());
        assertEquals(2, member.getComments().size());
        assertEquals(2, member2.getComments().size());
    }

    @DisplayName("대댓글을 삭제한다.")
    @Test
    void deleteChildComment() {
        // given
        Member member = new Member("member-1", "pwd");
        memberRepository.save(member);

        Member member2 = new Member("member-2", "pwd");
        memberRepository.save(member2);

        Billboard billboard = new Billboard("board-1");
        billboardRepository.save(billboard);

        Post post = new Post("post-1", member, billboard, "content");
        postRepository.save(post);

        CommentRequest commentRequest = new CommentRequest(post.getId(), member.getId(), "comment-1");
        Long commentId = commentService.writeComment(commentRequest);

        CommentRequest commentRequest2 = new CommentRequest(post.getId(), member2.getId(), "child-1 of comment-1", commentId);
        Long commentId2 = commentService.writeComment(commentRequest2);

        CommentRequest commentRequest3 = new CommentRequest(post.getId(), member.getId(), "child-2 of comment-1", commentId);
        Long commentId3 = commentService.writeComment(commentRequest3);

        CommentRequest commentRequest4 = new CommentRequest(post.getId(), member2.getId(), "child-1 of child-2 of comment-1", commentId3);
        Long commentId4 = commentService.writeComment(commentRequest4);

        // when #1 - Delete a comment having a child comment.
        commentService.deleteComment(commentId3);
        // then #1
        assertEquals(4, postService.getComments(post.getId()).size());
        assertEquals(1, member.getComments().size());
        assertEquals(2, commentService.findCommentById(commentId).getChildComments().size());
        Comment comment3 = commentRepository.findById(commentId3).orElseThrow();
        assertTrue(comment3.isVestige());
        assertEquals("[삭제된 댓글입니다]", comment3.getContent());

        // when #2 - Delete a comment not having a child comment.
        commentService.deleteComment(commentId2);
        // then #2
        assertEquals(3, postService.getComments(post.getId()).size());
        assertEquals(1, member2.getComments().size());
        assertEquals(1, commentService.findCommentById(commentId).getChildComments().size());
        assertThrowsExactly(CommentException.class, () -> commentService.findCommentById(commentId2));

        // when #3 - Delete a child comment having a parent comment which has been set as deleted.
        commentService.deleteComment(commentId4);
        // then #3
        assertEquals(1, postService.getComments(post.getId()).size());
        assertEquals(0, member2.getComments().size());
        assertEquals(0, commentService.findCommentById(commentId).getChildComments().size());
        assertThrowsExactly(CommentException.class, () -> commentService.findCommentById(commentId4));
        assertThrowsExactly(CommentException.class, () -> commentService.findCommentById(commentId3));
    }

    @Test
    void findCommentsByMemberId() {
        // given
        Member member = new Member("member-1", "pwd");
        memberRepository.save(member);

        Billboard billboard = new Billboard("board-1");
        billboardRepository.save(billboard);

        Billboard billboard2 = new Billboard("board-2");
        billboardRepository.save(billboard2);

        Post post = new Post("post-1 of board-1", member, billboard, "content");
        postRepository.save(post);

        Post post2 = new Post("post-1 of board-2", member, billboard2, "content");
        postRepository.save(post2);

        CommentRequest commentRequest = new CommentRequest(post.getId(), member.getId(), "comment-1 in post-1");
        Long commentId = commentService.writeComment(commentRequest);

        CommentRequest commentRequest2 = new CommentRequest(post.getId(), member.getId(), "child-1 of comment-1 in post-1", commentId);
        Long commentId2 = commentService.writeComment(commentRequest2);

        CommentRequest commentRequest3 = new CommentRequest(post2.getId(), member.getId(), "comment-1 in post-2");
        Long commentId3 = commentService.writeComment(commentRequest3);

        // when

        // then
    }
}