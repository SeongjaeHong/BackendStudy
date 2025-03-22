package mini_site.mini_site.service.billboard;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.user.User;
import mini_site.mini_site.exception.BillboardException;
import mini_site.mini_site.repository.billboard.BillboardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillboardService {
    private final BillboardRepository billboardRepository;

    public Billboard registerBillboard(String name, User user) {
        // TODO: Validate user level. Only Admin can register billboards.

        Billboard billboard = new Billboard(name);
        billboardRepository.save(billboard);
        return billboard;
    }

    public Billboard findBillboardById(Long billboardId) {
        return billboardRepository.findById(billboardId).orElseThrow(() -> new BillboardException("요청한 게시판을 찾을 수 없습니다."));
    }

    // 현제 페이지 게시글 보여주기
    // 이전/다음 페이지 이동
    // 게시글 검색
}
