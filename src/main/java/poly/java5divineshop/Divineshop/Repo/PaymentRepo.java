package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import poly.java5divineshop.Divineshop.Data.Entity.PaymentE;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentE, Integer> {
    PaymentE findByTenuser(String tenuser);

    List<PaymentE> findAllByTenuser(String tenuser);

    PaymentE save(PaymentE paymentE);

    @Modifying
    @Transactional
    @Query(value = "UPDATE [Payment] SET trangthai = :trangthai WHERE mota = :maDonHang", nativeQuery = true)
    int UpdatePaymentByuser(@Param("trangthai") Boolean trangthai, @Param("maDonHang") String maDonHang);

}
