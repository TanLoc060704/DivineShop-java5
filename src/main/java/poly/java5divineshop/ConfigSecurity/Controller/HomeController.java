package poly.java5divineshop.ConfigSecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    // add a requeest mapping for /leader
    @GetMapping("/leaders")
    public String showLeaders(){
        return "leaders";
    }

    // add a request mapping for / systems
    @GetMapping("/systems")
    public String showSystems(){
        return "systems";
    }
 
    @GetMapping("/userinfo")
    public String showUserInfo(){
        return "UserInfo";
    }

    @GetMapping("/cart")
    public String showCart(){
        return "cart";
    }
}
