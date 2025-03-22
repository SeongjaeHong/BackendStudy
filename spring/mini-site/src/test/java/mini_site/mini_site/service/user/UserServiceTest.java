package mini_site.mini_site.service.user;

import mini_site.mini_site.domain.user.User;
import mini_site.mini_site.domain.user.UserLevel;
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

    @DisplayName("사용자 ID를 이용해 사용자를 조회한다.")
    @Test
    void findUserById() {
        User user = userService.registerUser("name1", "pass");
        User foundUser = userService.findUserById(user.getId());
        assertEquals(user, foundUser);
    }

    @DisplayName("사용자를 등록한다.")
    @Test
    void registerUser() {
        User user = userService.registerUser("name1", "pass");
        assertEquals("name1", user.getName());
        assertEquals("pass", user.getPassword());
        assertEquals(UserLevel.MEMBER, user.getUserLevel());
    }
}