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

    @PostMapping("/saveAccountByUser")
    public ResponseEntity<?> saveAccountByUser(@RequestBody AccountDTO accountDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (accountService.findByUsername(accountDTO.getUsername()) != null) {
                result.put("status", true);
                result.put("message", "Username already exists");
                return ResponseEntity.ok(result);
            }
            if (accountService.findByEmail(accountDTO.getEmail()) != null) {
                result.put("status", true);
                result.put("message", "Email already exists");
                return ResponseEntity.ok(result);
            }
            String Otp = OTPUtil.generateOTP();
            httpSession.setAttribute("otp", Otp);
            httpSession.setAttribute("otpTime", System.currentTimeMillis());
            httpSession.setAttribute("account", accountDTO);
            accountService.sendMailForUser(accountDTO.getEmail(), Otp,"Mã OTP cho đăng ký tài khoản");
            result.put("status", true);
            result.put("message", "Call Api Successfully");
        } catch (Exception e) {
            log.error("Call api thất bại: /register ", e);
            result.put("status", false);
            result.put("message", "Call Api Failed");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/verify-otp/{otp}")
    public ResponseEntity<?> verifyOTP(@PathVariable("otp") String otp) {
        Map<String, Object> result = new HashMap<>();
        try {

            String otpSession = (String) httpSession.getAttribute("otp");
            Long otpTime = (Long) httpSession.getAttribute("otpTime");
            if (otpTime == null || (System.currentTimeMillis() - otpTime) > 1 * 60 * 1000) {
                result.put("status", true);
                result.put("message", "OTP đã hết hạn");
            } else if (otp.equals(otpSession)) {
                AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("account");
                accountDTO.setEnabled(true);
                String password = AccountDTO.passwordEncoder.encode(accountDTO.getHashedPassword());
                accountDTO.setHashedPassword(password);
                accountService.saveAccount(accountDTO);
                result.put("status", true);
                result.put("message", "Call Api Successfully");
            } else {
                result.put("status", true);
                result.put("message", "Otp does not exist");
            }
        } catch (Exception e) {
            log.error("Call api thất bại: /register ", e);
            result.put("success", false);
            result.put("message", "Call Api Failed");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/resendOtp")
    public ResponseEntity<?> resendOtp() {
        Map<String, Object> result = new HashMap<>();
        try {
            String Otp = OTPUtil.generateOTP();
            httpSession.setAttribute("otp", Otp);
            httpSession.setAttribute("otpTime", System.currentTimeMillis());
            AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("account");
            accountService.sendMailForUser(accountDTO.getEmail(), Otp,"Mã OTP cho đăng ký tài khoản");
            result.put("status", true);
            result.put("message", "Call Api Successfully");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Failed");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/forgotPW")
    public ResponseEntity<?> forgotPW(@RequestBody AccountDTO accountDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (accountService.findByEmail(accountDTO.getEmail()) == null) {
                result.put("status", true);
                result.put("message", "Email does not exist");
                return ResponseEntity.ok(result);
            }
            String Otp = OTPUtil.generateOTP();
            httpSession.setAttribute("otpForgotPW", Otp);
            httpSession.setAttribute("otpTimeForgotPW", System.currentTimeMillis());
            httpSession.setAttribute("accountForgotPW", accountDTO);
            accountService.sendMailForUser(accountDTO.getEmail(), Otp,"Mã OTP cho thay đổi mật khẩu");
            result.put("status", true);
            result.put("message", "Call Api Successfully");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Failed");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/verify-otp-change-pw/{otp}")
    public ResponseEntity<?> verifyOTPChangePW(@PathVariable("otp") String otp) {
        Map<String, Object> result = new HashMap<>();
        try {
            String otpSession = (String) httpSession.getAttribute("otpForgotPW");
            Long otpTime = (Long) httpSession.getAttribute("otpTimeForgotPW");
            if (otpTime == null || (System.currentTimeMillis() - otpTime) > 1 * 60 * 1000) {
                result.put("status", true);
                result.put("message", "OTP đã hết hạn");
            } else if (otp.equals(otpSession)) {
                AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("accountForgotPW");
                String password = AccountDTO.passwordEncoder.encode(accountDTO.getHashedPassword());
                accountDTO.setHashedPassword(password);
                accountService.updatePassAccountByEmail(accountDTO);
                result.put("status", true);
                result.put("message", "Call Api Successfully");
            } else {
                result.put("status", true);
                result.put("message", "Otp does not exist");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call Api Failed");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/resendOtpForgotPW")
    public ResponseEntity<?> resendOtpForgotPW() {
        Map<String, Object> result = new HashMap<>();
        try {
            String Otp = OTPUtil.generateOTP();
            httpSession.setAttribute("otpForgotPW", Otp);
            httpSession.setAttribute("otpTimeForgotPW", System.currentTimeMillis());
            AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("accountForgotPW");
            accountService.sendMailForUser(accountDTO.getEmail(), Otp,"Mã OTP cho thay đổi mật khẩu");
            result.put("status", true);
            result.put("message", "Call Api Successfully");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Failed");
        }
        return ResponseEntity.ok(result);
    }
}
