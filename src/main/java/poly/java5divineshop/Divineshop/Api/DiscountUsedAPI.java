package poly.java5divineshop.Divineshop.Api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Data.Dto.DiscountUsedDTO;
import poly.java5divineshop.Divineshop.Service.DiscountUsedService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/discount-used")
@Slf4j
public class DiscountUsedAPI {

    @Autowired
    private DiscountUsedService discountUsedService;

    @GetMapping("/{userId}/{discountId}")
    public ResponseEntity<?> getDiscountUsedByUserAndDiscount(@PathVariable Integer userId, @PathVariable Integer discountId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<DiscountUsedDTO> discountUsed = discountUsedService.getDiscountUsedByUserIDAndDiscountID(userId, discountId);
            if (discountUsed.isPresent()) {
                result.put("success", true);
                result.put("message", "Lấy thông tin giảm giá đã sử dụng thành công");
                result.put("data", discountUsed.get());
            } else {
                result.put("success", false);
                result.put("message", "Không tìm thấy giảm giá đã sử dụng");
                result.put("data", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            log.error("Lấy thông tin giảm giá đã sử dụng thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Lấy thông tin giảm giá đã sử dụng thất bại");
            result.put("data", null);
            return ResponseEntity.internalServerError().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createDiscountUsed(@RequestBody DiscountUsedDTO discountUsedDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            DiscountUsedDTO createdDiscountUsed = discountUsedService.createDiscountUsed(discountUsedDTO);
            result.put("success", true);
            result.put("message", "Thêm giảm giá đã sử dụng thành công");
            result.put("data", createdDiscountUsed);
        } catch (Exception e) {
            log.error("Thêm giảm giá đã sử dụng thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Thêm giảm giá đã sử dụng thất bại");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}