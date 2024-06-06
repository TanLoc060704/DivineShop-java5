package poly.java5divineshop.Divineshop.Api;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Service.UserService;
import poly.java5divineshop.Divineshop.Utils.OTPUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/account")
public class AccountApi {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession httpSession;

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
