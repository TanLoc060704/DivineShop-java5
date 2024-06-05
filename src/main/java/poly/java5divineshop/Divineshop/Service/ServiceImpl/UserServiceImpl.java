package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.UserDto;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;
import poly.java5divineshop.Divineshop.Data.Model.UserM;
import poly.java5divineshop.Divineshop.Repo.UserRepo;
import poly.java5divineshop.Divineshop.Service.UserService;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public List<UserM> getAllUser() {
        return UserM.convertListUserEToListUserM(userRepo.getAllUser());
    }

    @Override
    public UserM getUserByTenDangNhap(String tenDangNhap) throws SQLException {
        return UserM.convertUserEToUserM(userRepo.getUserByTenDangNhap(tenDangNhap));
    }

    @Override
    public UserM getUserByUsernameAndRole(String username, String role) {
        UserE user = userRepo.getUserByUsernameAndRole(username, role);
        return user == null ? null : UserM.convertUserEToUserM(user);
    }

    @Override
    public int updateUserByTenDangNhap(UserDto userDto) throws SQLException {
        return userRepo.updateUserByTenDangNhap(userDto.getHoVaTen(), userDto.getAnhDaiDien(), userDto.getTenDangNhap());
    }
}
