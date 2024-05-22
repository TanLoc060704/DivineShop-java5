package poly.java5divineshop.Divineshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LayoutController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("view", "home");
        return "user/index";
    }
    @GetMapping("/all-products")
    public String allProducts(Model model) {
        model.addAttribute("view", "allProducts");
        return "user/index";
    }
    @GetMapping("/detail")
    public String detail(Model model) {
        model.addAttribute("view", "detailProduct");
        return "user/index";
    }
    @GetMapping("/user-info")
    public String userInfo(Model model) {
        model.addAttribute("view", "userInfo");
        return "user/index";
    }
    @GetMapping("/order-history")
    public String orderHistory(Model model) {
        model.addAttribute("view", "userOrderHistory");
        return "user/index";
    }
    @GetMapping("/security")
    public String userSecurity(Model model) {
        model.addAttribute("view", "userPW&Security");
        return "user/index";
    }
    @GetMapping("/transaction")
    public String userTransaction(Model model) {
        model.addAttribute("view", "userTransaction");
        return "user/index";
    }
    @GetMapping("/favorite")
    public String userFavorite(Model model) {
        model.addAttribute("view", "userFavorites");
        return "user/index";
    }
    @GetMapping("/add-funds")
    public String addFunds(Model model) {
        model.addAttribute("view", "addFunds");
        return "user/index";
    }
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("view", "cart");
        return "user/index";
    }
    @GetMapping("/empty-cart")
    public String emptyCart(Model model) {
        model.addAttribute("view", "emptyCart");
        return "user/index";
    }
    @GetMapping("/confirm-cart")
    public String confirmcart(Model model) {
        model.addAttribute("view", "confirmCart");
        return "user/index";
    }
    @GetMapping("/pay-cart")
    public String payCart(Model model) {
        model.addAttribute("view", "payCart");
        return "user/index";
    }
}
