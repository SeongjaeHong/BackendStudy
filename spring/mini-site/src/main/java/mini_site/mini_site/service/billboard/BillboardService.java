package mini_site.mini_site.service.billboard;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.billboard.Post;
import mini_site.mini_site.domain.member.MemberLevel;
import mini_site.mini_site.exception.BillboardException;
import mini_site.mini_site.repository.billboard.BillboardRepository;
import mini_site.mini_site.service.billboard.dto.response.PostResponse;
import mini_site.mini_site.service.member.dto.response.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BillboardService {
    private final BillboardRepository billboardRepository;

    @Transactional
    public Billboard registerBillboard(String name, MemberResponse memberResponse) throws BillboardException {
        if (memberResponse.memberLevel() != MemberLevel.ADMIN) {
            throw new BillboardException("관리자 계정만 새로운 게시판을 만들 수 있습니다.");
        }

        Billboard billboard = new Billboard(name);
        billboardRepository.save(billboard);
        return billboard;
    }

    public Billboard findBillboardById(Long billBoardId) {
        return billboardRepository.findById(billBoardId).orElseThrow(() -> new BillboardException("요청한 게시판을 찾을 수 없습니다."));
    }

    public List<PostResponse> findPostsByBoardId(Long billBoardId) {
        Billboard billboard = billboardRepository.findById(billBoardId).orElseThrow(() -> new BillboardException("요청한 게시판을 찾을 수 없습니다."));

        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : billboard.getPosts()) {
            postResponses.add(
                    new PostResponse(
                            post.getId(),
                            post.getTitle(),
                            post.getCreatedAt(),
                            post.getMember().getName(),
                            billBoardId)
            );
        }
        return postResponses;
    }

    // 현제 페이지 게시글 보여주기
    // 이전/다음 페이지 이동
    // 게시글 검색
}
