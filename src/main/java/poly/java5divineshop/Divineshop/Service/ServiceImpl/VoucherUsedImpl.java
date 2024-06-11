package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.VoucherUsedDTO;
import poly.java5divineshop.Divineshop.Data.Entity.VoucherUsedE;
import poly.java5divineshop.Divineshop.Data.Model.VoucherUsedM;
import poly.java5divineshop.Divineshop.Repo.VoucherUsedRepo;
import poly.java5divineshop.Divineshop.Service.VoucherUsedService;

import java.util.Date;
import java.util.Optional;

@Service
public class VoucherUsedImpl implements VoucherUsedService {
    @Autowired
    private VoucherUsedRepo voucherUsedRepository;


    @Override
    public Optional<VoucherUsedDTO> getVoucherUsedByUserIDAndVoucherID(Integer userId, Integer VoucherId) {
        return voucherUsedRepository.findBySysIdUserAndSysIdVoucher(userId, VoucherId)
                .map(VoucherUsedM::convertVoucherUsedEToVoucherUsedDTO);
    }

    @Override
    public VoucherUsedDTO createVoucherUsed(VoucherUsedDTO voucherUsedDTO) {
        VoucherUsedE voucherUsedE = VoucherUsedM.convertVoucherUsedDTOToVoucherUsedE(voucherUsedDTO);
        voucherUsedE.setNgaySuDung(new Date());
        VoucherUsedE savedVoucherUsedE = voucherUsedRepository.save(voucherUsedE);
        return VoucherUsedM.convertVoucherUsedEToVoucherUsedDTO(savedVoucherUsedE);
    }
}
