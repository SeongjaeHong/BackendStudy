package mini_site.mini_site.service.billboard.dto.request;

public record PostRequest(
        String postTitle,
        Long memberId,
        Long billboardId,
        String content
) { }
