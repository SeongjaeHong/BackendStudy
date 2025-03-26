package mini_site.mini_site.service.billboard.dto.request;

public record PostRequest(
        Long memberId,
        Long billboardId,
        String content
) { }
