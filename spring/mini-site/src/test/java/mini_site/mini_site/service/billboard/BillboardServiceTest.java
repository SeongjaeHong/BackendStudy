package mini_site.mini_site.service.billboard;

import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.domain.user.User;
import mini_site.mini_site.exception.BillboardException;
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
        Billboard billboard = billboardService.registerBillboard("test", new User());
        assertEquals("test", billboard.getName());
    }

    @DisplayName("게시판을 조회한다.")
    @Test
    void findBillboardById() {
        Billboard billboard = billboardService.registerBillboard("test", new User());
        assertEquals(billboard, billboardService.findBillboardById(billboard.getId()));
        assertThrows(BillboardException.class, () -> billboardService.findBillboardById(-1L));
    }
}