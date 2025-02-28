package jpashop_re_group.jpashop_re.controller;

import jpashop_re_group.jpashop_re.domain.item.Book;
import jpashop_re_group.jpashop_re.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/items/new")
    public String createRegisterForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String registerProduct(BookForm form) {
        Book book = new Book();
        book.setAttributes(form);

        productService.save(book);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String products(Model model) {
        model.addAttribute("items", productService.findProducts());
        return "items/itemList";
    }
}
