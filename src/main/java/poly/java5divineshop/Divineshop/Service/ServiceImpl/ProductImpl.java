package poly.java5divineshop.Divineshop.Service.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;
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
    public ProductM addProduct(ProductM productM) {
        // Chuyển đổi ProductM sang ProductE
        ProductE productE = ProductM.convertProductMToProductE(productM);
        productE.setCategories(null);

        // Có thì thêm ko có thì tự tạo mới xong thêm
        for (CategoryM category : productM.getCategories()) {

            Optional<CategoryE> categoryE = categoryRepo.findByTenTheLoai(category.getTenTheLoai());

            if (categoryE.isEmpty()) {
                CategoryE newCategory = new CategoryE(category.getTenTheLoai());
                CategoryE savedCategoryE = categoryRepo.save(newCategory);
                productE.addCateList(savedCategoryE);
            }else{
                productE.addCateList(categoryE.get());
            }
            System.out.println(category.getTenTheLoai() + category.getId());
        }
        // Thực hiện thêm mới sản phẩm
        productE = productRepository.save(productE);
        // Trả về sản phẩm đã thêm mới
        return ProductM.convertProductEToProductM(productE);
    }

    @Override
    public Optional<ProductM> updateProduct(int id, ProductM productM) {

        ProductE productE = ProductM.convertProductMToProductE(productM);
        productE.setCategories(null);

        // Có thì thêm ko có thì tự tạo mới xong thêm
        for (CategoryM category : productM.getCategories()) {

            Optional<CategoryE> categoryE = categoryRepo.findByTenTheLoai(category.getTenTheLoai());

            if (categoryE.isEmpty()) {
                CategoryE newCategory = new CategoryE(category.getTenTheLoai());
                CategoryE savedCategoryE = categoryRepo.save(newCategory);
                productE.addCateList(savedCategoryE);
            }else{
                productE.addCateList(categoryE.get());
            }
            System.out.println(category.getTenTheLoai() + category.getId());
        }

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
                    existingProduct.setCategories(productE.getCategories());
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

    @Override
    public Page<ProductM> getAllProductsByPage(String searchTerm, String category, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());
        if (StringUtils.isEmpty(searchTerm) && StringUtils.isEmpty(category)) {
            return ProductM.convertPageProductEToPageProductM(productRepository.findAll(pageable));
        } else if (!StringUtils.isEmpty(searchTerm) && StringUtils.isEmpty(category)) {
            return ProductM.convertPageProductEToPageProductM(productRepository.findByTenSanPhamContainingIgnoreCase(searchTerm, pageable));
        } else if (StringUtils.isEmpty(searchTerm) && !StringUtils.isEmpty(category)) {
            return ProductM.convertPageProductEToPageProductM(productRepository.findByDanhMucIgnoreCase(category, pageable));
        } else {
            return ProductM.convertPageProductEToPageProductM(productRepository.findByTenSanPhamContainingIgnoreCaseAndDanhMucIgnoreCase(searchTerm, category, pageable));
        }
    }

    @Override
    public List<ProductM> getTop8ByCustomOrder() {
        return ProductM.convertListProductEToListProductM(productRepository.findTop8ByCustomOrder());
    }

    @Override
    public List<ProductM> getTop8ByOrderBySoLuongMuaDesc() {
        return ProductM.convertListProductEToListProductM(productRepository.findTop8ByOrderBySoLuongMuaDesc());
    }

    @Override
    public List<ProductM> getTop8ByOrderByPercentGiamGiaDesc() {
        return ProductM.convertListProductEToListProductM(productRepository.findTop8ByOrderByPercentGiamGiaDesc());
    }

}
