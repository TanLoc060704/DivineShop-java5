package poly.java5divineshop.Divineshop;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LayoutController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("view", "home");
        return "user/index";
    }
    @GetMapping("/log-in")
    public String logIn(Model model) {
        return "user/logIn";
    }
    @GetMapping("/register")
    public String register(Model model) {
        return "user/register";
    }

    @GetMapping("/confirm-otp")
    public String showConfirmOtp(){
        return "user/confirmOtp";
    }

    @GetMapping("/confirm-otp-forgot-pw")
    public String showConfirmOtpForgotPW(){
        return "user/confirmOtpForgotPW";
    }

    @GetMapping("/forgot-password")
    public String forgotPW(Model model) {
        return "user/forgotPW";
    }
    @GetMapping("/all-products")
    public String allProducts(Model model) {
        model.addAttribute("view", "allProducts");
        return "user/index";
    }
    @GetMapping("/detail/{}")
    public String detail(Model model) {
        model.addAttribute("view", "detailProduct");
        return "user/index";
    }
    @GetMapping("/user-info")
    public String userInfo(Model model, HttpSession session) {
        session.removeAttribute("flag");
        session.removeAttribute("flag1");
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
    public String addFunds(Model model, HttpSession session) {
        Boolean flag = (Boolean) session.getAttribute("flag");
        model.addAttribute("flag", flag);
        model.addAttribute("flag1",session.getAttribute("flag1") != null ? true : false);
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
    @GetMapping("/order-history/{}")
    public String orderhistorydetaill(Model model) {
        model.addAttribute("view", "userOrderHistoryDetaill");
        return "user/index";
    }
}
