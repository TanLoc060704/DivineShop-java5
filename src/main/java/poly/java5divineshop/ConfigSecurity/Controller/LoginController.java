package poly.java5divineshop.ConfigSecurity.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipal;
@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){

        return "plain-login";
    }

    // add request mapping for / access-denied
    @GetMapping("/access-denied")
    public String showAccessDenied(){

        return "access-denied";
    }

}
