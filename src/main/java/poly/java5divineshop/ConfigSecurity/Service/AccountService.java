package poly.java5divineshop.ConfigSecurity.Service;

import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Model.AccountM;

import java.util.List;


public interface AccountService {
    List<AccountM> getAllAccount();
    AccountM findByUsername(String username);
    AccountE findByUsernameSecurity(String username);
}
