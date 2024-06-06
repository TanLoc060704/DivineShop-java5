package poly.java5divineshop.Divineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.java5divineshop.Divineshop.Data.Model.UserM;
import poly.java5divineshop.Divineshop.Service.UserService;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/account-view")
    public String accountView(Model model) {
        model.addAttribute("view", "account/overview");
        return "admin/admin";
    }

    @GetMapping("/account-list")
    public String accountList(Model model) {
        model.addAttribute("view", "account/list");
        return "admin/admin";
    }

    @GetMapping("/product-view")
    public String productView(Model model) {
        model.addAttribute("view", "product/overview");
        return "admin/admin";
    }

    @GetMapping("/product-list")
    public String productList(Model model) {
        model.addAttribute("view", "product/list");
        return "admin/admin";
    }

    @GetMapping("/category-view/{}")
    public String categoryView(Model model) {
        model.addAttribute("view", "category/overview");
        return "admin/admin";
    }

    @GetMapping("/category-list")
    public String categoryList(Model model) {
        model.addAttribute("view", "category/list");
        return "admin/admin";
    }

    @GetMapping("/discount-view")
    public String discountView(Model model) {
        model.addAttribute("view", "discount/overview");
        return "admin/admin";
    }

    @GetMapping("/discount-list")
    public String discountList(Model model) {
        model.addAttribute("view", "discount/list");
        return "admin/admin";
    }
}
