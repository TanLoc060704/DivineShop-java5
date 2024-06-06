package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Repo.AccountRepo;
import poly.java5divineshop.Divineshop.Service.UserService;
@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    HttpSession session;

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
