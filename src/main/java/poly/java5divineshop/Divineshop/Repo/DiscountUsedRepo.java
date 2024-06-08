package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.DiscountUsedE;

import java.util.Optional;

@Repository
public interface DiscountUsedRepo extends JpaRepository<DiscountUsedE, Integer> {
    Optional<DiscountUsedE> findBySysIdUserAndSysIdDiscount(Integer sysIdUser, Integer sysIdDiscount);
}
