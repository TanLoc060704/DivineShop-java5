package poly.java5divineshop.Divineshop.Data.Model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserM {
    private Integer Id;
    private String ten_dang_nhap;
    private String email;
    private String ho_va_ten;
    private String so_du;
    private Date ngay_tham_gia;
    private String anh_dai_dien;

    public static UserM convertUserEToUserM(UserE userE) {
        return UserM.builder()
                .Id(userE.getId())
                .ten_dang_nhap(userE.getTen_dang_nhap())
                .email(userE.getEmail())
                .ho_va_ten(userE.getHo_va_ten())
                .so_du(userE.getSo_du())
                .ngay_tham_gia(userE.getNgay_tham_gia())
                .anh_dai_dien(String.valueOf(userE.getNgay_tham_gia()))
                .build();
    }

    public static List<UserM> convertListUserEToListUserM(List<UserE> userEList) {
        return  userEList.stream()
                .map(e -> convertUserEToUserM(e))
                .collect(Collectors.toList());
    }
}
