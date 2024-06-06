package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;

public interface UserService {
    AccountE save(AccountDTO accountDTO);

    void sendMailForUser(String email, String otp);
}
