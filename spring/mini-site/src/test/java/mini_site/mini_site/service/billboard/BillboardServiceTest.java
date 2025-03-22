package mini_site.mini_site.service.billboard;

import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.member.MemberLevel;
import mini_site.mini_site.exception.BillboardException;
import mini_site.mini_site.service.member.dto.response.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BillboardServiceTest {
    @Autowired BillboardService billboardService;

    @DisplayName("게시판을 등록한다.")
    @Test
    void registerBillboard() {
        // given
        MemberResponse memberResponse = new MemberResponse(1L, "admin", MemberLevel.ADMIN);

        // when
        Billboard billboard = billboardService.registerBillboard("test", memberResponse);

        // then
        assertEquals("test", billboard.getName());
    }

    @DisplayName("게시판을 조회한다.")
    @Test
    void findBillboardById() {
        // given
        MemberResponse memberResponse = new MemberResponse(1L, "admin", MemberLevel.ADMIN);
        Billboard billboard = billboardService.registerBillboard("test", memberResponse);

        // when
        Billboard foundBillboard = billboardService.findBillboardById(billboard.getId());

        // then
        assertEquals(billboard, foundBillboard);
        assertThrows(BillboardException.class, () -> billboardService.findBillboardById(-1L));
    }
}