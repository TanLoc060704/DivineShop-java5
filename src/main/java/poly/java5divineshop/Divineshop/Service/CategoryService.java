package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.CategoryDTO;
import poly.java5divineshop.Divineshop.Data.Model.CategoryM;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryM> getAllCategories();

    Optional<CategoryM> getCategoryById(int id);

    CategoryM addCategory(CategoryM categoryM);

    CategoryM addCategoryByName(CategoryM categoryM);

    Optional<CategoryM> updateCategory(int id, CategoryM categoryM);

    boolean deleteCategory(int id);

    Optional<CategoryM> getCategoryByTenTheLoai(String tenTheLoai);
}
