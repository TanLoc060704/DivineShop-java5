package poly.java5divineshop.Divineshop.Api;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Data.Dto.UserDto;
import poly.java5divineshop.Divineshop.Data.Entity.RoleE;
import poly.java5divineshop.Divineshop.Data.Model.UserM;
import poly.java5divineshop.Divineshop.Service.UserService;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class UserDivinesShopApi {
    @Autowired
    UserService userService;

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Successfully");
            result.put("data", userService.getAllUser());
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getUserByUserName")
    public ResponseEntity<?> getUserByUserName(@RequestParam String username) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Successfully");
            result.put("data", userService.findBytenDangNhap(username));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getUserByUsernameAndRole")
    public ResponseEntity<?> getUserByUsernameAndRole(@RequestParam String username, @RequestParam String role) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Successfully");
            result.put("data", userService.getUserByUsernameAndRole(username, role));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> saveUser(@RequestParam String soTien, @RequestParam String tenDangNhap) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Successfully");
            result.put("data", userService.updateUserByTenDangNhap(soTien, tenDangNhap) == 1 ? "Cập nhật thành công" : "Cập nhật thất bại");
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Failed");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateUserByTenDangNhap")
    public ResponseEntity<?> updateUserByTenDangNhap(@RequestBody UserDto userDto) {
        Map<String, Object> result = new HashMap<>();
        try {
            String base64Data = userDto.getAnhDaiDien();
            System.out.println(base64Data);
            if (base64Data != null && !base64Data.isEmpty()) {
                String name = base64Data.substring(0, base64Data.indexOf(","));
                int viTriDauPhayThuHai = base64Data.indexOf(",", base64Data.indexOf(",") + 1);
                String data = base64Data.substring(viTriDauPhayThuHai + 1);
                byte[] bytes = Base64.decodeBase64(data);
                File file = new File("src/main/resources/static/img-user", name);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.close();
                userDto.setAnhDaiDien(name);
            } else {
                UserM userM = userService.getUserByTenDangNhap(userDto.getTenDangNhap());
                if (userM != null) {
                    userDto.setAnhDaiDien(userM.getAnhDaiDien());
                }
            }
            result.put("status", true);
            result.put("message", "Call Api Successfully");
            result.put("data", userService.updateUserByTenDangNhap(userDto));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", false);
            result.put("message", "Call Api Failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getUserByUsername")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Successfully");
            result.put("data", userService.getUserByTenDangNhap(username));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}

