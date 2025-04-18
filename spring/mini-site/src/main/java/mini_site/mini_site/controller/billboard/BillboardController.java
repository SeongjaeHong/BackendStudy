package mini_site.mini_site.controller.billboard;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.billboard.Billboard;
import mini_site.mini_site.service.billboard.BillboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BillboardController {
    private final BillboardService billboardService;

    @GetMapping(value = {"/", "/{boardName}"})
    public String home(@PathVariable(required = false) String boardName, Model model){
        if (boardName == null) {
            boardName = "일반 게시판";
        }

        Billboard foundBoard = billboardService.findBillboardByName(boardName);
        model.addAttribute("posts", foundBoard.getPosts());
        model.addAttribute("boardName", foundBoard.getName());
        model.addAttribute("boards", billboardService.findAllBillboards());
        return "home";
    }

    // 글 작성 페이지
    @GetMapping("/write")
    public String writePost(@RequestParam("board") String boardName, Model model) {
        Billboard foundBoard = billboardService.findBillboardByName(boardName);
        model.addAttribute("boardName", foundBoard.getName());
        model.addAttribute("boards", billboardService.findAllBillboards());
        return "write";
    }
}
