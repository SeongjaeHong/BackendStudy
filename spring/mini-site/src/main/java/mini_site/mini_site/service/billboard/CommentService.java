package mini_site.mini_site.service.billboard;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.billboard.Comment;
import mini_site.mini_site.domain.billboard.Post;
import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.exception.CommentException;
import mini_site.mini_site.repository.billboard.CommentRepository;
import mini_site.mini_site.service.billboard.dto.request.CommentRequest;
import mini_site.mini_site.service.billboard.dto.response.CommentResponse;
import mini_site.mini_site.service.member.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final PostService postService;

    public Long writeComment(CommentRequest commentRequest) {
        Member member = memberService.findMemberById(commentRequest.memberId());
        Post post = postService.findPostById(commentRequest.postId());
        Comment parentComment = (commentRequest.parentCommentId() != null) ? findCommentById(commentRequest.parentCommentId()) : null;
        Comment comment = new Comment(member, post, commentRequest.content(), parentComment);

        commentRepository.save(comment);
        addCommentToEntity(comment);
        return comment.getId();
    }
    
    public void deleteComment(Long commentId) {
        Comment comment = findCommentById(commentId);

        deleteCommentFromEntity(comment);
    }

    public List<CommentResponse> findCommentsByMemberId(Long memberId) {
        Member member = memberService.findMemberById(memberId);

        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : member.getComments()) {
            commentResponses.add(
                    new CommentResponse(
                            comment.getId(),
                            memberId,
                            member.getName(),
                            comment.getContent(),
                            comment.getCreatedAt()
                    )
            );
        }
        return commentResponses;
    }

    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentException("요청한 댓글을 찾을 수 없습니다."));
    }

    private void addCommentToEntity(Comment comment) {
        // Add in the post.
        comment.getPost().addComment(comment);

        // Add in the member.
        comment.getMember().addComment(comment);

        // If it's a child comment, add below the parent comment.
        if (comment.getParentComment() != null) {
            comment.getParentComment().addChildComment(comment);
        }
    }

    private void deleteCommentFromEntity(Comment comment) {
        // Delete from the member.
        comment.getMember().deleteComment(comment);

        if (comment.getChildComments().isEmpty()) {
            comment.getPost().deleteComment(comment);
            if (comment.getParentComment() != null) {
                comment.getParentComment().deleteChildComment(comment);
            }
            commentRepository.delete(comment);

            deleteVestigialComments(comment);
        } else {
            comment.setAsDeleted();
        }
    }

    /*
    When parent comment is deleted first and then child comment is deleted,
    the parent comment will remain as a vestigial comment forever.
    Whenever child comment is deleted, find all vestigial parent comments, having no child comment, and delete them.
     */
    private void deleteVestigialComments(Comment comment) {
        Comment parentComment = comment.getParentComment();
        if (parentComment == null) {
            return;
        }

        if (parentComment.isVestige() && parentComment.getChildComments().isEmpty()) {
            parentComment.getPost().deleteComment(parentComment);
            if (parentComment.getParentComment() != null) {
                parentComment.getParentComment().deleteChildComment(parentComment);
            }
            commentRepository.delete(parentComment);
            deleteVestigialComments(parentComment);
        }
    }
}
