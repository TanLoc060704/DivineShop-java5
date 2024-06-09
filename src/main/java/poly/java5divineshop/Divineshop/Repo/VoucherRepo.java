package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.VoucherE;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface VoucherRepo extends JpaRepository<VoucherE, Integer> {
    Optional<VoucherE> findByCodeVoucher(String codeVoucher);
    @Query("SELECT v FROM VoucherE v WHERE " +
            "(LOWER(v.VoucherName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR :searchTerm IS NULL) " +
            "AND (:startDate IS NULL OR v.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR v.endDate <= :endDate)")
    Page<VoucherE> findAllByFilter(@Param("searchTerm") String searchTerm,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate,
                                   Pageable pageable);
}