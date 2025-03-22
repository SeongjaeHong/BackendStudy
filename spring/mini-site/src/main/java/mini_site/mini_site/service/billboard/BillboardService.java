package mini_site.mini_site.service.billboard;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.user.UserLevel;
import mini_site.mini_site.exception.BillboardException;
import mini_site.mini_site.repository.billboard.BillboardRepository;
import mini_site.mini_site.service.user.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillboardService {
    private final BillboardRepository billboardRepository;

    public Billboard registerBillboard(String name, UserResponse userResponse) throws BillboardException {
        if (userResponse.userLevel() != UserLevel.ADMIN) {
            throw new BillboardException("관리자 계정만 새로운 게시판을 만들 수 있습니다.");
        }

        Billboard billboard = new Billboard(name);
        billboardRepository.save(billboard);
        return billboard;
    }

    public Billboard findBillboardById(Long billBoardId) {
        return billboardRepository.findById(billBoardId).orElseThrow(() -> new BillboardException("요청한 게시판을 찾을 수 없습니다."));
    }

    // 현제 페이지 게시글 보여주기
    // 이전/다음 페이지 이동
    // 게시글 검색
}
