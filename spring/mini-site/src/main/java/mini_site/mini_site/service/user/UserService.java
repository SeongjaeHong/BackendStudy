package mini_site.mini_site.service.user;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.user.User;
import mini_site.mini_site.exception.UserException;
import mini_site.mini_site.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException("요청한 사용자를 찾을 수 없습니다."));
    }

    public User registerUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }
}
