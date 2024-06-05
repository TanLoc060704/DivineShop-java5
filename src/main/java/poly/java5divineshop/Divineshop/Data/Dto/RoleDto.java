package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poly.java5divineshop.Divineshop.Data.Entity.RoleE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Integer idRole;
    private String username;
    private String usernameUser;
    private String role;

    public static RoleE convertRoleDTOToRoleE(RoleDto RoleDTO) {
        return RoleE.builder()
                .idRole(RoleDTO.getIdRole())
                .username(RoleDTO.getUsername())
                .usernameUser(RoleDTO.getUsernameUser())
                .role(RoleDTO.getRole())
                .build();
    }
}
