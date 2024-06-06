package poly.java5divineshop.Divineshop.Api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Data.Dto.CategoryDTO;
import poly.java5divineshop.Divineshop.Data.Model.CategoryM;
import poly.java5divineshop.Divineshop.Service.CategoryService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryAPI {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(CategoryM.convertListCategoryMToListCategoryDTO(categoryService.getAllCategories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<CategoryM> category = categoryService.getCategoryById(id);
            if (category.isPresent()) {
                result.put("success", true);
                result.put("message", "Lấy thông tin danh mục thành công");
                result.put("data", category.get());
            } else {
                result.put("success", false);
                result.put("message", "Danh mục không tồn tại");
                result.put("data", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            log.error("Lấy thông tin danh mục thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Lấy thông tin danh mục thất bại");
            result.put("data", null);
            return ResponseEntity.internalServerError().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            CategoryM createdCategory = categoryService.addCategoryByName(CategoryM.convertCategoryDTOToCategoryM(categoryDTO));
            result.put("success", true);
            result.put("message", "Thêm danh mục thành công");
            result.put("data", createdCategory);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Thêm danh mục thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Thêm danh mục thất bại");
            result.put("data", null);
            return ResponseEntity.internalServerError().body(result);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<CategoryM> updatedCategory = categoryService.updateCategory(id, CategoryM.convertCategoryDTOToCategoryM(categoryDTO));
            if (updatedCategory.isPresent()) {
                result.put("success", true);
                result.put("message", "Cập nhật danh mục thành công");
                result.put("data", updatedCategory.get());
            } else {
                result.put("success", false);
                result.put("message", "Danh mục không tồn tại");
                result.put("data", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            log.error("Cập nhật danh mục thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Cập nhật danh mục thất bại");
            result.put("data", null);
            return ResponseEntity.internalServerError().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean isDeleted = categoryService.deleteCategory(id);
            if (isDeleted) {
                result.put("success", true);
                result.put("message", "Xoá danh mục thành công");
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "Danh mục không tồn tại");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            log.error("Xoá danh mục thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Xoá danh mục thất bại");
            return ResponseEntity.internalServerError().body(result);
        }
    }
}
