package poly.java5divineshop.Divineshop.Data.Model;

import jakarta.persistence.*;
import lombok.*;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Entity.RoleE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserM {

    private Integer sysIdUser;
    private String tenDangNhap;
    private String email;
    private String hoVaTen;
    private String soDu;
    private Date ngayThamGia;
    private String anhDaiDien;
    private List<RoleE> roles;

    public static UserM convertUserEToUserM(UserE userE) {
        return UserM.builder()
                .sysIdUser(userE.getSysIdUser())
                .tenDangNhap(userE.getTenDangNhap())
                .email(userE.getEmail())
                .hoVaTen(userE.getHoVaTen())
                .soDu(userE.getSoDu())
                .ngayThamGia(userE.getNgayThamGia())
                .anhDaiDien(userE.getAnhDaiDien())
                .roles(userE.getRoles())
                .build();
    }

    public static List<UserM> convertListUserEToListUserM(List<UserE> userEList) {
        return userEList.stream()
                .map(e -> convertUserEToUserM(e))
                .collect(Collectors.toList());
    }
}
