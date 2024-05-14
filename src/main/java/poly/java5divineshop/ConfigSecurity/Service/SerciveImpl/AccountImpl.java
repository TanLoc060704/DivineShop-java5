package poly.java5divineshop.ConfigSecurity.Service.SerciveImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.ConfigSecurity.Service.AccountService;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Model.AccountM;
import poly.java5divineshop.Divineshop.Repo.AccountRepo;

import java.util.List;
@Service
public class AccountImpl implements AccountService {
    @Autowired
    AccountRepo repo;
    @Override
    public List<AccountM> getAllAccount() {
        List<AccountE> accountES = repo.findAll();
        return AccountM.convertListAccountEToListAccountM(accountES);
    }

    @Override
    public AccountE findByUsername(String username) {
//        AccountE accountE = repo.findByUsername(username);
//        return AccountM.convertAccountEToAccountM(accountE);
        return repo.findByUsername(username);
    }
}
