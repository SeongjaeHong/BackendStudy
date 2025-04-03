package mini_site.mini_site.service.billboard.dto.response;

public record CommentResponse(
        Long commentId,
        Long memberId,
        String memberName,
        String content,
        String createdAt
) {
}
