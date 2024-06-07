package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.UserDto;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;
import poly.java5divineshop.Divineshop.Data.Model.UserM;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<UserM> getAllUser() throws SQLException;

    UserM getUserByTenDangNhap(String tenDangNhap) throws SQLException;

    UserM getUserByUsernameAndRole(String username, String role) throws SQLException;

    int updateUserByTenDangNhap(UserDto userDto) throws SQLException;
    
    List<UserM> findAll();
}
