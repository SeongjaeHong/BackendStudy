package mini_site.mini_site.service.user;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.user.User;
import mini_site.mini_site.exception.UserException;
import mini_site.mini_site.repository.user.UserRepository;
import mini_site.mini_site.service.user.dto.request.RegisterUserRequest;
import mini_site.mini_site.service.user.dto.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    private User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException("요청한 사용자를 찾을 수 없습니다."));
    }

    public UserResponse findUserById(Long userId) {
        User foundUser = getUserById(userId);

        return new UserResponse(foundUser.getId(), foundUser.getName(), foundUser.getUserLevel());
    }

    public UserResponse registerUser(RegisterUserRequest registerUserRequest) {
        User user = registerUserRequest.toUser();
        userRepository.save(user);
        return new UserResponse(user.getId(), user.getName(), user.getUserLevel());
    }
}
