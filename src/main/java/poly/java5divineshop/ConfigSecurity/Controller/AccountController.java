package poly.java5divineshop.ConfigSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import poly.java5divineshop.ConfigSecurity.Service.AccountService;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Model.AccountM;

import java.util.List;

@RestController
@RequestMapping("/systems")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/getallaccount")
    public ResponseEntity<?> getallaccount(){
         List<AccountM> list=  accountService.getAllAccount();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<?> findByUsername(@RequestParam String username){
        AccountE accountE = accountService.findByUsername(username);
        return ResponseEntity.ok(accountE);
    }
}
