package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.OderE;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;
import poly.java5divineshop.Divineshop.Data.Model.OderM;
import poly.java5divineshop.Divineshop.Data.Model.ProductM;

import java.util.List;

@Repository
public interface OderRepo extends JpaRepository<OderE, Integer> {
    List<OderE> findAll();

    List<OderE> findAllByProductE(ProductE e);

    List<OderE> findAllByUserE(UserE e);

    OderE findByProductE(ProductE e);

    OderE findByUserE(UserE e);

    OderE save(OderE oderE);

    List<OderE> findAllByMaDonHang(String maDonHang);

}
