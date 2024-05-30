package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Model.ProductM;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductM> getAllProducts();
    Optional<ProductM> getProductById(int id);
    ProductM addProduct(ProductM productM);
    Optional<ProductM> updateProduct(int id, ProductM productM);
    boolean deleteProduct(int id);

}
