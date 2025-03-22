package mini_site.mini_site.service.user.dto.response;

import mini_site.mini_site.domain.user.UserLevel;

public record UserResponse(
        Long userId,
        String name,
        UserLevel userLevel
) {
}
