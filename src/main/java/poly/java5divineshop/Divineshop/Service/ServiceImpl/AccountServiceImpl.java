package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Service.AccountService;
import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Model.AccountM;
import poly.java5divineshop.Divineshop.Repo.AccountRepo;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepo repo;
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    HttpSession session;

    @Override
    public List<AccountM> getAllAccount() {
        List<AccountE> accountES = repo.findAll();
        return AccountM.convertListAccountEToListAccountM(accountES);
    }

    @Override
    public AccountM findByUsername(String username) {
        AccountE accountE = repo.getAccountByUsername(username);
        return accountE == null ? null : AccountM.convertAccountEToAccountM(accountE);
    }

    @Override
    public AccountE findByUsernameSecurity(String username) {
        return repo.findByUsernameQuerySecurity(username);
    }

    @Override
    public AccountM findByEmail(String email) {
        AccountE accountE = repo.findByEmail(email);
        return accountE == null ? null : AccountM.convertAccountEToAccountM(accountE);
    }

    @Override
    public AccountE saveAccount(AccountDTO accountDTO) {
        return repo.save(AccountDTO.convertAccountDTOToAccountE(accountDTO));
    }

    @Override
    public void sendMailForUser(String email, String otp,String subject) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText("Mã OTP của bạn là: " + otp);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int updatePassAccountByEmail(AccountDTO accountDTO) {
        return repo.updatePassAccountByEmail(accountDTO.getHashedPassword(), accountDTO.getEmail());
    }
}
