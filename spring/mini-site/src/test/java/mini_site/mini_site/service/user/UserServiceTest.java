package mini_site.mini_site.service.user;

import mini_site.mini_site.domain.user.User;
import mini_site.mini_site.domain.user.UserLevel;
import mini_site.mini_site.repository.user.UserRepository;
import mini_site.mini_site.service.user.dto.request.RegisterUserRequest;
import mini_site.mini_site.service.user.dto.response.UserResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @DisplayName("사용자 ID를 이용해 사용자를 조회한다.")
    @Test
    void findUserById() {
        // given
        RegisterUserRequest registerUserRequest = new RegisterUserRequest("name1", "pass");
        UserResponse userResponse = userService.registerUser(registerUserRequest);

        // when
        UserResponse foundUser = userService.findUserById(userResponse.userId());

        // then
        assertAll(
                () -> Assertions.assertThat(foundUser.name()).isEqualTo(userResponse.name()),
                () -> Assertions.assertThat(foundUser.userLevel()).isEqualTo(userResponse.userLevel())
        );
    }

    @DisplayName("사용자를 등록한다.")
    @Test
    void registerUser() {
        // given
        RegisterUserRequest registerUserRequest = new RegisterUserRequest("name1", "pass");
        User user = userRepository.save(new User("name1", "pass"));

        // when
        UserResponse userResponse = userService.registerUser(registerUserRequest);
        User foundUser = userRepository.findById(user.getId()).get();

        // then
        assertAll(
                // Check name
                () -> Assertions.assertThat(foundUser.getName()).isEqualTo(user.getName()),
                () -> Assertions.assertThat(foundUser.getName()).isEqualTo(userResponse.name()),

                // Check default userLevel
                () -> Assertions.assertThat(foundUser.getUserLevel()).isEqualTo(userResponse.userLevel())
        );
    }

    @DisplayName("사용자에게 관리자 권한을 부여한다.")
    @Test
    void grantAdmin() {
        // given
        RegisterUserRequest registerUserRequest = new RegisterUserRequest("name1", "pass");
        UserResponse userResponse = userService.registerUser(registerUserRequest);

        // when
        userService.grantUser(userResponse.userId());
        UserResponse foundUser = userService.findUserById(userResponse.userId());

        // then
        assertEquals(UserLevel.ADMIN, foundUser.userLevel());
    }
}