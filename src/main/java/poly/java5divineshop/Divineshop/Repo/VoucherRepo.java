package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.VoucherE;

import java.util.Optional;

@Repository
public interface VoucherRepo extends JpaRepository<VoucherE, Integer> {
    Optional<VoucherE> findByCodeVoucher(String codeVoucher);
}