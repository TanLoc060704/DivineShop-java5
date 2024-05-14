package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<AccountE,Integer> {
    List<AccountE> findAll();
    AccountE findByUsername(String username);
}
