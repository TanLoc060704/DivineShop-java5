package poly.java5divineshop.Divineshop.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poly.java5divineshop.Divineshop.Service.AccountService;
import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class AccountApi {
    @Autowired
    AccountService accountService;

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
}
