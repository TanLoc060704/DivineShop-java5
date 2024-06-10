package poly.java5divineshop.Divineshop.Service;

import java.sql.SQLException;
import java.util.List;

import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Data.Dto.UserDto;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;
import poly.java5divineshop.Divineshop.Data.Model.UserM;

public interface UserService {
    List<UserM> getAllUser() throws SQLException;

    UserM getUserByTenDangNhap(String tenDangNhap) throws SQLException;

    UserM getUserByUsernameAndRole(String username, String role) throws SQLException;

    int updateUserByTenDangNhap(UserDto userDto) throws SQLException;

    UserE findBytenDangNhap(String tenDangNhap);

    int updateUserByTenDangNhap(String sotien , String tenDangNhap);
}
