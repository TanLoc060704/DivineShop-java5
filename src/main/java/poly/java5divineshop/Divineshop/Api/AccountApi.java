package poly.java5divineshop.Divineshop.Api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import poly.java5divineshop.Divineshop.Utils.OTPUtil;
import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Service.AccountService;
import poly.java5divineshop.Divineshop.Service.UserService;

@RestController
@Slf4j
@RequestMapping("/api/public")
public class AccountApi {
    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    HttpSession httpSession;

    @PostMapping("/saveAccount")
    public ResponseEntity<?> saveUser(@RequestBody AccountDTO accountDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (accountService.findByUsername(accountDTO.getUsername()) != null) {
                result.put("status", true);
                result.put("message", "Username already exists");
                result.put("data", null);
                return ResponseEntity.ok(result);
            }
            if (accountService.findByEmail(accountDTO.getEmail()) != null) {
                result.put("status", true);
                result.put("message", "Email already exists");
                result.put("data", null);
                return ResponseEntity.ok(result);
            }
            accountDTO.setHashedPassword("$2a$12$B93B8OIKPMQdFyI1kOgmY.RvqtTS98PJkNYc7wGTz/e9imf.e8EAa");
            accountDTO.setEnabled(true);
            result.put("status", true);
            result.put("message", "Call Api Successfully");
            result.put("data", accountService.saveAccount(accountDTO));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", false);
            result.put("message", "Call Api Failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/send-email-for-user")
    public ResponseEntity<?> sendEmailForUser(@RequestBody() AccountDTO accountDTO){
        Map<String, Object> result = new HashMap<>();
        try {
            String Otp = OTPUtil.generateOTP();
            httpSession.setAttribute("otp",Otp);
            httpSession.setAttribute("account",accountDTO);
            userService.sendMailForUser(accountDTO.getEmail(),Otp);
            result.put("success",true);
            result.put("message","Call api thành công");
            result.put("data",Otp);
        }catch (Exception e){
            log.error("Call api thất bại: /register ",e);
            result.put("success",false);
            result.put("message","Call api thất bại");
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/verify-otp/{otp}")
    public ResponseEntity<?> verifyOTP(@PathVariable ("otp") String otp){
        Map<String, Object> result = new HashMap<>();
        try {
            String otpSession = (String) httpSession.getAttribute("otp");
            if(otp.equals(otpSession)){
                AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("account");
//                accountDTO.setHashedPassword(accountDTO.getHashedPassword());
                userService.save(accountDTO);
                result.put("success",true);
                result.put("message","OTP đúng");
            }else {
                result.put("success",false);
                result.put("message","OTP sai");
            }
        }catch (Exception e){
            log.error("Call api thất bại: /register ",e);
            result.put("success",false);
            result.put("message","Call api thất bại");
        }
        return ResponseEntity.ok(result);
    }
}
