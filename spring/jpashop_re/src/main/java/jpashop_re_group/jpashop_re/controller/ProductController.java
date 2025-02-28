package jpashop_re_group.jpashop_re.controller;

import jpashop_re_group.jpashop_re.domain.Product;
import jpashop_re_group.jpashop_re.domain.item.Book;
import jpashop_re_group.jpashop_re.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/items/{id}/edit")
    public String createEditForm(@PathVariable("id")Long itemId, Model model) {
        BookForm form = new BookForm();
        Book book = (Book) productService.findProductById(itemId);
        form.setAttribute(book);
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{id}/edit")
    public String editProduct(@PathVariable Long id, @ModelAttribute("form") BookForm form) {
        Book book = (Book) productService.findProductById(id);
        book.setAttributes(form);
        productService.save(book);
        return "redirect:/items";
    }
}
