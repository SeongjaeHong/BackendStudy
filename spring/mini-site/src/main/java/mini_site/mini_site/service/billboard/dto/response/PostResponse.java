package mini_site.mini_site.service.billboard.dto.response;

public record PostResponse(
        Long postId,
        Long memberId,
        Long billboardId,
        String content
) {
}
