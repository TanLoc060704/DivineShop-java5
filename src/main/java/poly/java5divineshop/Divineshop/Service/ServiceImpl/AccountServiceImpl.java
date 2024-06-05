package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Service.AccountService;
import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Model.AccountM;
import poly.java5divineshop.Divineshop.Repo.AccountRepo;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepo repo;

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
}
