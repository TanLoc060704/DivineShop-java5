package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.DiscountE;

import java.util.Optional;

@Repository
public interface DiscountRepo extends JpaRepository<DiscountE, Integer> {
    Optional<DiscountE> findByCodeDiscount(String codeDiscount);
}