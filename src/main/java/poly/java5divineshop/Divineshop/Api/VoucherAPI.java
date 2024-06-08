package poly.java5divineshop.Divineshop.Api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.Divineshop.Data.Dto.VoucherDTO;
import poly.java5divineshop.Divineshop.Data.Model.VoucherM;
import poly.java5divineshop.Divineshop.Service.VoucherService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vouchers")
@Slf4j
public class VoucherAPI {

    @Autowired
    private VoucherService voucherService;

    @GetMapping
    public ResponseEntity<?> getAllVouchers() {
        return ResponseEntity.ok(voucherService.getAllVouchers());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getVoucherById(@PathVariable int id) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            Optional<VoucherDTO> Voucher = VoucherService.getVoucherById(id);
//            if (Voucher.isPresent()) {
//                result.put("success", true);
//                result.put("message", "Lấy thông tin giảm giá thành công");
//                result.put("data", Voucher.get());
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
    public ResponseEntity<?> getVoucherByCode(@PathVariable String code) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<VoucherDTO> Voucher = voucherService.getVoucherByCodeVoucher(code);
            if (Voucher.isPresent()) {
                result.put("success", true);
                result.put("message", "Lấy thông tin giảm giá thành công");
                result.put("data", Voucher.get());
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
    public ResponseEntity<?> createVoucher(@RequestBody VoucherDTO voucherDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            voucherService.createVoucher(voucherDTO);
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
    public ResponseEntity<?> updateVoucher(@PathVariable int id, @RequestBody VoucherDTO voucherDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<VoucherM> updatedVoucher = voucherService.updateVoucher(id, voucherDTO);
            if (updatedVoucher.isPresent()) {
                result.put("success", true);
                result.put("message", "Cập nhật giảm giá thành công");
                result.put("data", updatedVoucher.get());
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
    public ResponseEntity<?> deleteVoucher(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean isDeleted = voucherService.deleteVoucher(id);
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
