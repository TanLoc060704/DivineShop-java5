package poly.java5divineshop.Divineshop.Api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Data.Dto.DiscountDTO;
import poly.java5divineshop.Divineshop.Data.Model.DiscountM;
import poly.java5divineshop.Divineshop.Service.DiscountService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
@Slf4j
public class DiscountAPI {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public ResponseEntity<?> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getDiscountById(@PathVariable int id) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            Optional<DiscountDTO> discount = discountService.getDiscountById(id);
//            if (discount.isPresent()) {
//                result.put("success", true);
//                result.put("message", "Lấy thông tin giảm giá thành công");
//                result.put("data", discount.get());
//            } else {
//                result.put("success", false);
//                result.put("message", "Không tìm thấy giảm giá");
//                result.put("data", null);
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
//            }
//        } catch (Exception e) {
//            log.error("Lấy thông tin giảm giá thất bại!!!", e);
//            result.put("success", false);
//            result.put("message", "Lấy thông tin giảm giá thất bại");
//            result.put("data", null);
//            return ResponseEntity.internalServerError().body(result);
//        }
//        return ResponseEntity.ok(result);
//    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getDiscountByCode(@PathVariable String code) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<DiscountDTO> discount = discountService.getDiscountByCodeDiscount(code);
            if (discount.isPresent()) {
                result.put("success", true);
                result.put("message", "Lấy thông tin giảm giá thành công");
                result.put("data", discount.get());
            } else {
                result.put("success", false);
                result.put("message", "Không tìm thấy giảm giá");
                result.put("data", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            log.error("Lấy thông tin giảm giá thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Lấy thông tin giảm giá thất bại");
            result.put("data", null);
            return ResponseEntity.internalServerError().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createDiscount(@RequestBody DiscountDTO discountDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            discountService.createDiscount(discountDTO);
            result.put("success", true);
            result.put("message", "Thêm giảm giá thành công");
        } catch (Exception e) {
            log.error("Thêm giảm giá thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Thêm giảm giá thất bại");
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable int id, @RequestBody DiscountDTO discountDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<DiscountM> updatedDiscount = discountService.updateDiscount(id, discountDTO);
            if (updatedDiscount.isPresent()) {
                result.put("success", true);
                result.put("message", "Cập nhật giảm giá thành công");
                result.put("data", updatedDiscount.get());
            } else {
                result.put("success", false);
                result.put("message", "Không tìm thấy giảm giá");
                result.put("data", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            log.error("Cập nhật giảm giá thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Cập nhật giảm giá thất bại");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean isDeleted = discountService.deleteDiscount(id);
            if (isDeleted) {
                result.put("success", true);
                result.put("message", "Xoá giảm giá thành công");
            } else {
                result.put("success", false);
                result.put("message", "Không tìm thấy giảm giá");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            log.error("Xoá giảm giá thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Xoá giảm giá thất bại");
        }
        return ResponseEntity.ok(result);
    }
}
