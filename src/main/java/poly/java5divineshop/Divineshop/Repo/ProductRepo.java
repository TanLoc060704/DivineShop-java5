package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;

public interface ProductRepo extends JpaRepository<ProductE, Integer> {
}
