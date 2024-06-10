package poly.java5divineshop.Divineshop.Api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Data.Dto.VoucherUsedDTO;
import poly.java5divineshop.Divineshop.Service.VoucherUsedService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/voucher-used")
@Slf4j
public class VoucherUsedAPI {

    @Autowired
    private VoucherUsedService voucherUsedService;

    @GetMapping("/{userId}/{VoucherId}")
    public ResponseEntity<?> getVoucherUsedByUserAndVoucher(@PathVariable Integer userId, @PathVariable Integer VoucherId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<VoucherUsedDTO> VoucherUsed = voucherUsedService.getVoucherUsedByUserIDAndVoucherID(userId, VoucherId);
            if (VoucherUsed.isPresent()) {
                result.put("success", true);
                result.put("message", "Lấy thông tin giảm giá đã sử dụng thành công");
                result.put("data", VoucherUsed.get());
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
    public ResponseEntity<?> createVoucherUsed(@RequestBody VoucherUsedDTO voucherUsedDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            VoucherUsedDTO createdVoucherUsed = voucherUsedService.createVoucherUsed(voucherUsedDTO);
            result.put("success", true);
            result.put("message", "Thêm giảm giá đã sử dụng thành công");
            result.put("data", createdVoucherUsed);
        } catch (Exception e) {
            log.error("Thêm giảm giá đã sử dụng thất bại!!!", e);
            result.put("success", false);
            result.put("message", "Thêm giảm giá đã sử dụng thất bại");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}