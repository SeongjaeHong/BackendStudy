package mini_site.mini_site.service.user.dto.request;

import mini_site.mini_site.domain.user.User;

public record RegisterUserRequest (
        String name,
        String password
) {
    public User toUser() {
        return User.builder()
                .name(name)
                .password(password)
                .build();
    }
}
