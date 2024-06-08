package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.VoucherDTO;
import poly.java5divineshop.Divineshop.Data.Model.VoucherM;

import java.util.List;
import java.util.Optional;

public interface VoucherService {
    List<VoucherDTO> getAllVouchers();

    Optional<VoucherDTO> getVoucherById(Integer id);

    Optional<VoucherDTO> getVoucherByCodeVoucher(String code);

    VoucherDTO createVoucher(VoucherDTO voucherDTO);

    Optional<VoucherM> updateVoucher(Integer id, VoucherDTO voucherDTO);

    boolean deleteVoucher(Integer id);
}
