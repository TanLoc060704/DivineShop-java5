package poly.java5divineshop.ConfigSecurity.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Service.AccountService;
import poly.java5divineshop.Divineshop.Data.Dto.UserRequest;
import poly.java5divineshop.Divineshop.Data.Model.AccountM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/systems")
@Slf4j
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/get-all-account")
    public ResponseEntity<?> getAllAccount(){
        List<AccountM> list =  accountService.getAllAccount();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find-account-by-username")
    public ResponseEntity<?> findAccountByUsername(@RequestParam String username){
        Map<String, Object> result = new HashMap<>();
        try {
            AccountM accountM = accountService.findByUsername(username);
            result.put("success",true);
            result.put("message","Call api thành công");
            result.put("data",accountM);
            if(Objects.isNull(accountM)){
                result.put("success",true);
                result.put("message","Account không tồn tại");
            }
        }catch (Exception e){
            log.error("Call api thất bại: /find-account-by-username ",e);
            result.put("success",false);
            result.put("message","Call api thất bại");
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Call api thành công");
            result.put("data",userRequest);
        }catch (Exception e){
            log.error("Call api thất bại: /register ",e);
            result.put("success",false);
            result.put("message","Call api thất bại");
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }
}
