package poly.java5divineshop.Divineshop.Service;

import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.OderDTO;
import poly.java5divineshop.Divineshop.Data.Entity.OderE;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;
import poly.java5divineshop.Divineshop.Data.Model.OderM;

import java.util.List;

public interface OderService {
    List<OderM> findAll();

    List<OderM> findAllByidproductE(ProductE e);

    List<OderM> findAllByiduserE(UserE e);

    OderM findByidproductE(ProductE e);

    OderM findByiduserE(UserE e);

    void save(OderE oderE);
}
