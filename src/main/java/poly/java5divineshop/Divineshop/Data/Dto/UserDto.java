package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poly.java5divineshop.Divineshop.Data.Entity.RoleE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer sysIdUser;
    private String tenDangNhap;
    private String email;
    private String hoVaTen;
    private String soDu;
    private Date ngayThamGia;
    private String anhDaiDien;
    private List<RoleE> roles;

    public static UserE convertUserDtoToUserE(UserDto userDto) {
        return UserE.builder()
                .sysIdUser(userDto.sysIdUser)
                .tenDangNhap(userDto.tenDangNhap)
                .email(userDto.email)
                .hoVaTen(userDto.hoVaTen)
                .soDu(userDto.soDu)
                .ngayThamGia(userDto.ngayThamGia)
                .anhDaiDien(userDto.anhDaiDien)
                .roles(userDto.getRoles())
                .build();
    }

    public static List<UserE> convertListUserDtoToListUserE(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(e -> convertUserDtoToUserE(e))
                .collect(Collectors.toList());
    }


}
