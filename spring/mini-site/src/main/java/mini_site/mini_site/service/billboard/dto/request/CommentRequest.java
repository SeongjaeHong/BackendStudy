package mini_site.mini_site.service.billboard.dto.request;

public record CommentRequest(
        Long postId,
        Long memberId,
        String content,
        Long parentCommentId
) {
    public CommentRequest {}  // 대댓글
    public CommentRequest(Long postId, Long memberId, String content) {
        this(postId, memberId, content, null);  // 댓글
    }
}
