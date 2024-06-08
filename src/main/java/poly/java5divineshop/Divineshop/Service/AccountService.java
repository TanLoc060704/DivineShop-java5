package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Model.AccountM;

import java.sql.SQLException;
import java.util.List;


public interface AccountService {
    List<AccountM> getAllAccount();

    AccountM findByUsername(String username);

    AccountE findByUsernameSecurity(String username);

    AccountM findByEmail(String email) throws SQLException;

    AccountE saveAccount(AccountDTO accountDTO) throws SQLException;

    void sendMailForUser(String email, String otp);
}
