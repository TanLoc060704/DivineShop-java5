package poly.java5divineshop.Divineshop.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import poly.java5divineshop.Divineshop.Data.Entity.CategoryE;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Model.CategoryM;
import poly.java5divineshop.Divineshop.Data.Model.ProductM;
import poly.java5divineshop.Divineshop.Repo.CategoryRepo;
import poly.java5divineshop.Divineshop.Repo.ProductRepo;
import poly.java5divineshop.Divineshop.Service.ImageService;
import poly.java5divineshop.Divineshop.Service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ImageService imageService;

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
    public ProductM addProduct(ProductM productM, MultipartFile file) {
        // Chuyển đổi ProductM sang ProductE
        ProductE productE = ProductM.convertProductMToProductE(productM);
        productE.setCategories(null);

        // Có thì thêm ko có thì tự tạo mới xong thêm phải để là 0 khi tìm category nếu không thấy trên js
        for (CategoryM category : productM.getCategories()) {
            CategoryE existingCategory = categoryRepo.findById(category.getId())
                    .orElseGet(() -> {
                        CategoryE newCategory = new CategoryE(category.getTenTheLoai());
                        return categoryRepo.save(newCategory);
                    });
            productE.addCateList(existingCategory);
            System.out.println(category.getTenTheLoai() + category.getId());
        }
        productE.setAnhSanPham(imageService.saveImage(file));
        // Thực hiện thêm mới sản phẩm
        productE = productRepository.save(productE);
        // Trả về sản phẩm đã thêm mới
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
                    existingProduct.setCategories(CategoryM.convertListCategoryMToListCategoryE(productM.getCategories()));
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

    @Override
    public Optional<ProductM> getProductBySlug(String slug) {
        return productRepository.findProductBySlug(slug)
                .map(ProductM::convertProductEToProductM);
    }

}
