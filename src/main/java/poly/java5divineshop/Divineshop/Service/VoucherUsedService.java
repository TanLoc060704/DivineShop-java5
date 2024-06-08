package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.VoucherUsedDTO;

import java.util.Optional;

public interface VoucherUsedService {
    Optional<VoucherUsedDTO> getVoucherUsedByUserIDAndVoucherID(Integer userId, Integer VoucherId);
    VoucherUsedDTO createVoucherUsed(VoucherUsedDTO voucherUsedDTO);
}
