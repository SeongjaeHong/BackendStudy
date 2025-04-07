package mini_site.mini_site.controller.billboard;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.service.billboard.BillboardService;
import mini_site.mini_site.service.billboard.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BillboardController {
    private final BillboardService billboardService;

    @GetMapping("/")
    public String base(){
        return "redirect:/ordinary";
    }

    @GetMapping("/ordinary")
    public String home(Model model){
        Billboard foundBoard = billboardService.findBillboardById(1L);
        model.addAttribute("posts", foundBoard.getPosts());
        model.addAttribute("boardName", "일반 게시판");
        model.addAttribute("boards", billboardService.findAllBillboards());
        return "home";
    }
}
