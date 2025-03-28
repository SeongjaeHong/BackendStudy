package mini_site.mini_site.service.billboard.dto.response;

public record PostResponse(
        Long postId,
        String postTitle,
        String createdAt,
        String memberName,
        Long billboardId
) {
}
