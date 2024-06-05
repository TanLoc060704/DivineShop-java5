package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<ProductE, Integer> {
    @Query("SELECT p FROM ProductE p LEFT JOIN FETCH p.categories WHERE p.slug = :slug")
    Optional<ProductE> findProductBySlug(@Param("slug") String slug);

}
