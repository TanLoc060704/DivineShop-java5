package poly.java5divineshop.Divineshop.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Entity.CategoryE;
import poly.java5divineshop.Divineshop.Data.Model.CategoryM;
import poly.java5divineshop.Divineshop.Repo.CategoryRepo;
import poly.java5divineshop.Divineshop.Service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryM> getAllCategories() {
        return CategoryM.convertListCategoryEToListCategoryM(categoryRepo.findAll());
    }

    @Override
    public Optional<CategoryM> getCategoryById(int id) {
        return categoryRepo.findById(id)
                .map(CategoryM::convertCategoryEToCategoryM);
    }

    @Override
    public CategoryM addCategory(CategoryM categoryM) {
        CategoryE categoryE = CategoryM.convertCategoryMToCategoryE(categoryM);
        categoryE = categoryRepo.save(categoryE);
        return CategoryM.convertCategoryEToCategoryM(categoryE);
    }

    @Override
    public CategoryM addCategoryByName(CategoryM categoryM) {
        // Tìm kiếm category trong repository bằng tên
        Optional<CategoryE> categoryE = categoryRepo.findByTenTheLoai(categoryM.getTenTheLoai());

        if (categoryE.isPresent()) {
            // Nếu category đã tồn tại, trả về category tương ứng
            return CategoryM.convertCategoryEToCategoryM(categoryE.get());
        } else {
            // Nếu category chưa tồn tại, tạo mới và lưu vào repository
            CategoryE newCategory = new CategoryE(categoryM.getTenTheLoai());
            CategoryE savedCategoryE = categoryRepo.save(newCategory);
            return CategoryM.convertCategoryEToCategoryM(savedCategoryE);
        }
    }

    @Override
    public Optional<CategoryM> updateCategory(int id, CategoryM categoryM) {
        return categoryRepo.findById(id)
                .map(existingCategory -> {
                    existingCategory.setTenTheLoai(categoryM.getTenTheLoai());
                    categoryRepo.save(existingCategory);
                    return CategoryM.convertCategoryEToCategoryM(existingCategory);
                });
    }

    @Override
    public boolean deleteCategory(int id) {
        return categoryRepo.findById(id)
                .map(category -> {
                    categoryRepo.delete(category);
                    return true;
                }).orElse(false);
    }

    @Override
    public Optional<CategoryM> getCategoryByTenTheLoai(String tenTheLoai) {
        return categoryRepo.findByTenTheLoai(tenTheLoai)
                .map(CategoryM::convertCategoryEToCategoryM);
    }
}
