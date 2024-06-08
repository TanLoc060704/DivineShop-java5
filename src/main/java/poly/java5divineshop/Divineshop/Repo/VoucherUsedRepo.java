package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.VoucherUsedE;

import java.util.Optional;

@Repository
public interface VoucherUsedRepo extends JpaRepository<VoucherUsedE, Integer> {
    Optional<VoucherUsedE> findBySysIdUserAndSysIdVoucher(Integer sysIdUser, Integer sysIdVoucher);
}
