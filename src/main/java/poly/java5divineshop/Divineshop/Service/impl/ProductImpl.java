package poly.java5divineshop.Divineshop.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Model.ProductM;
import poly.java5divineshop.Divineshop.Repo.ProductRepo;
import poly.java5divineshop.Divineshop.Service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepo productRepository;


    @Override
    public List<ProductM> getAllProducts() {
        return ProductM.convertListProductEToListProductM(productRepository.findAll());
    }

    @Override
    public Optional<ProductM> getProductById(int id) {
        return productRepository.findById(id)
                .map(ProductM::convertProductEToProductM);
    }

    @Override
    public ProductM addProduct(ProductM productM) {
        ProductE productE = ProductE.builder()
                .maSanPham(productM.getMaSanPham())
                .tenSanPham(productM.getTenSanPham())
                .tinhTrang(productM.isTinhTrang())
                .theLoai(productM.getTheLoai())
                .giaSanPham(productM.getGiaSanPham())
                .percentGiamGia(productM.getPercentGiamGia())
                .anhSanPham(productM.getAnhSanPham())
                .slug(productM.getSlug())
                .danhMuc(productM.getDanhMuc())
                .mota(productM.getMota())
                .activeSanPham(productM.isActiveSanPham())
                .sysIdDiscount(productM.getSysIdDiscount())
                .soLuong(productM.getSoLuong())
                .soLuongMua(productM.getSoLuongMua())
                .soLuotThich(productM.getSoLuotThich())
                .build();
        productE = productRepository.save(productE);
        return ProductM.convertProductEToProductM(productE);
    }

    @Override
    public Optional<ProductM> updateProduct(int id, ProductM productM) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setMaSanPham(productM.getMaSanPham());
                    existingProduct.setTenSanPham(productM.getTenSanPham());
                    existingProduct.setTinhTrang(productM.isTinhTrang());
                    existingProduct.setTheLoai(productM.getTheLoai());
                    existingProduct.setGiaSanPham(productM.getGiaSanPham());
                    existingProduct.setPercentGiamGia(productM.getPercentGiamGia());
                    existingProduct.setAnhSanPham(productM.getAnhSanPham());
                    existingProduct.setSlug(productM.getSlug());
                    existingProduct.setDanhMuc(productM.getDanhMuc());
                    existingProduct.setMota(productM.getMota());
                    existingProduct.setActiveSanPham(productM.isActiveSanPham());
                    existingProduct.setSysIdDiscount(productM.getSysIdDiscount());
                    existingProduct.setSoLuong(productM.getSoLuong());
                    existingProduct.setSoLuongMua(productM.getSoLuongMua());
                    existingProduct.setSoLuotThich(productM.getSoLuotThich());
                    productRepository.save(existingProduct);
                    return ProductM.convertProductEToProductM(existingProduct);
                });
    }


    @Override
    public boolean deleteProduct(int id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return true;
                }).orElse(false);
    }
}
