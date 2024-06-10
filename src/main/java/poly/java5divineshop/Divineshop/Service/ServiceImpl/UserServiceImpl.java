package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import java.sql.SQLException;
import java.util.List;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Data.Dto.UserDto;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;
import poly.java5divineshop.Divineshop.Data.Model.UserM;
import poly.java5divineshop.Divineshop.Repo.AccountRepo;
import poly.java5divineshop.Divineshop.Repo.UserRepo;
import poly.java5divineshop.Divineshop.Service.UserService;

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
        return userRepo.updateUserByTenDangNhap(userDto.getHoVaTen(), userDto.getAnhDaiDien(),
                userDto.getTenDangNhap());
    }

    @Override
    public UserE findBytenDangNhap(String tenDangNhap) {
        return userRepo.findBytenDangNhap(tenDangNhap);
    }

    @Override
    public int updateUserByTenDangNhap(String sotien, String tenDangNhap) {
        return userRepo.updateUserByTenDangNhap(sotien, tenDangNhap);
    }
}
