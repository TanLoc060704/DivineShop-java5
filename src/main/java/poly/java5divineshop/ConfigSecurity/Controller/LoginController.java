package poly.java5divineshop.ConfigSecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        return "user/logIn";
    }

    // add request mapping for / access-denied
    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }

}
