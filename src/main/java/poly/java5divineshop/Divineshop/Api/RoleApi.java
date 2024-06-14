package poly.java5divineshop.Divineshop.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poly.java5divineshop.Divineshop.Data.Dto.RoleDto;
import poly.java5divineshop.Divineshop.Data.Model.RoleM;
import poly.java5divineshop.Divineshop.Service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class RoleApi {
    @Autowired
    RoleService roleService;

    @PostMapping("/updateRole")
    public ResponseEntity<?> updateRoleByUsername(@RequestBody List<RoleDto> list) {
        Map<String, Object> result = new HashMap<>();
        try {
            for (RoleDto roleDto : list) {
                RoleM existingRole = roleService.getRoleByUsernameAndRole(roleDto.getUsername(), roleDto.getRole());
                if (existingRole != null) {
                    roleService.deleteRoleByUsername(roleDto);
                } else {
                    roleService.saveRole(roleDto);
                }
            }
            result.put("status", true);
            result.put("message", "Call Api Successfully");
            result.put("data", null);

        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Failed");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}

