package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Model.ProductM;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductE, Integer> {
    @Query("SELECT p FROM ProductE p LEFT JOIN FETCH p.categories WHERE p.slug = :slug")
    Optional<ProductE> findProductBySlug(@Param("slug") String slug);

    Page<ProductE> findByTenSanPhamContainingIgnoreCaseAndDanhMucIgnoreCase(String searchTerm, String category, Pageable pageable);

    Page<ProductE> findByDanhMucIgnoreCase(String category, Pageable pageable);

    Page<ProductE> findByTenSanPhamContainingIgnoreCase(String searchTerm, Pageable pageable);

    @Query(value = "SELECT TOP 8 * FROM Product ORDER BY (soluongmua * 0.4 + soluotthich * 0.6) DESC", nativeQuery = true)
    List<ProductE> findTop8ByCustomOrder();
    List<ProductE> findTop8ByOrderBySoLuongMuaDesc();
    List<ProductE> findTop8ByOrderByPercentGiamGiaDesc();
}
