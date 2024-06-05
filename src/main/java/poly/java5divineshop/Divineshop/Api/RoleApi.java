package poly.java5divineshop.Divineshop.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poly.java5divineshop.Divineshop.Data.Dto.RoleDto;
import poly.java5divineshop.Divineshop.Service.RoleService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class RoleApi {
    @Autowired
    RoleService roleService;

    @PostMapping("/updateRoleByUsername")
    public ResponseEntity<?> updateRoleByUsername(@RequestBody RoleDto roleDto) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (roleService.getRoleByUsernameAndRole(roleDto.getUsername(), roleDto.getRole()) != null) {
                result.put("status", true);
                result.put("message", "Username already has this role");
                result.put("data", null);
                return ResponseEntity.ok(result);
            }
            result.put("status", true);
            result.put("message", "Call Api Successfully");
            result.put("data", roleService.updateRoleByUsername(roleDto));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", false);
            result.put("message", "Call Api Failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
