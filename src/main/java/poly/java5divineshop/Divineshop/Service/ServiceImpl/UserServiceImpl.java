package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5divineshop.Divineshop.Data.Dto.UserDto;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;
import poly.java5divineshop.Divineshop.Data.Model.UserM;
import poly.java5divineshop.Divineshop.Repo.UserRepo;
import poly.java5divineshop.Divineshop.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    HttpSession session;

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
    public AccountE save(AccountDTO accountDTO) {
        return accountRepo.save(AccountDTO.convertAccountDTOToAccountE(accountDTO));
    }

    @Override
    public void sendMailForUser(String email, String otp) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Mã OTP cho đăng ký tài khoản");
            helper.setText("Mã OTP của bạn là: " + otp);
            emailSender.send(message);
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
