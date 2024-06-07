package poly.java5divineshop.Divineshop.Data.Model;

import lombok.*;
import poly.java5divineshop.Divineshop.Data.Entity.RoleE;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleM {
    private Integer idRole;
    private String username;
    private String usernameUser;
    private String role;

    public static RoleM convertRoleEToRoleM(RoleE roleE) {
        return RoleM.builder()
                .idRole(roleE.getIdRole())
                .username(roleE.getUsername())
                .usernameUser(roleE.getUsernameUser())
                .role(roleE.getRole())
                .build();
    }

    public static List<RoleM> convertListRoleEToListRoleM(List<RoleE> RoleEList) {
        return RoleEList.stream()
                .map(e -> convertRoleEToRoleM(e))
                .collect(Collectors.toList());
    }
}
