package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.OderDTO;
import poly.java5divineshop.Divineshop.Data.Entity.OderE;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;
import poly.java5divineshop.Divineshop.Data.Model.OderM;
import poly.java5divineshop.Divineshop.Repo.OderRepo;
import poly.java5divineshop.Divineshop.Service.OderService;

import java.util.Collections;
import java.util.List;

@Service
public class OderImpl implements OderService {
    @Autowired
    OderRepo repo;

    @Override
    public List<OderM> findAll() {
        return OderM.convertListOderEToListOderM(repo.findAll());
    }

    @Override
    public List<OderM> findAllByidproductE(ProductE e) {
        return OderM.convertListOderEToListOderM(repo.findAllByProductE(e));
    }

    @Override
    public List<OderM> findAllByiduserE(UserE e) {
        return OderM.convertListOderEToListOderM(repo.findAllByUserE(e));
    }

    @Override
    public OderM findByidproductE(ProductE e) {
        return OderM.convertOderEToOderM(repo.findByProductE(e));
    }

    @Override
    public OderM findByiduserE(UserE e) {
        return OderM.convertOderEToOderM(repo.findByUserE(e));
    }

    @Override
    public void save(OderE oderE) {
        repo.save(oderE);
    }

    @Override
    public List<OderM> findAllByMaDonHang(String maDonHang) {
        return OderM.convertListOderEToListOderM(repo.findAllByMaDonHang(maDonHang));
    }
}
