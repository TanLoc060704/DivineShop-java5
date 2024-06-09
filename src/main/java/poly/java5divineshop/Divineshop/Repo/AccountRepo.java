package poly.java5divineshop.Divineshop.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;

@Repository
public interface AccountRepo extends JpaRepository<AccountE, Integer> {
    List<AccountE> findAll();

    @Query(value = "select a from AccountE a where a.username = :username")
    AccountE getAccountByUsername(@Param("username") String username);

    @Query("SELECT a FROM AccountE a LEFT JOIN FETCH a.roles WHERE a.username = :username or a.email = :username")
    AccountE findByUsernameQuerySecurity(@Param("username") String username);

    @Query("select a from AccountE a where a.email = :email")
    AccountE findByEmail(@Param("email") String email);

    AccountE save(AccountE account);

    @Modifying
    @Transactional
    @Query(value = "update Account set hash_password = :hashPassword where email = :email", nativeQuery = true)
    int updatePassAccountByEmail(@Param("hashPassword") String hashPassword, @Param("email") String email);
}
