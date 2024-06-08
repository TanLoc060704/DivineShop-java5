package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.VoucherDTO;
import poly.java5divineshop.Divineshop.Data.Entity.VoucherE;
import poly.java5divineshop.Divineshop.Data.Model.VoucherM;
import poly.java5divineshop.Divineshop.Repo.VoucherRepo;
import poly.java5divineshop.Divineshop.Service.VoucherService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoucherImpl implements VoucherService {

    private final VoucherRepo voucherRepository;

    @Autowired
    public VoucherImpl(VoucherRepo voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public VoucherDTO createVoucher(VoucherDTO voucherDTO) {
        VoucherE voucherE = VoucherM.convertVoucherDTOToVoucherE(voucherDTO);
        VoucherE savedVoucherE = voucherRepository.save(voucherE);
        return VoucherM.convertVoucherEToVoucherDTO(savedVoucherE);
    }

    @Override
    public List<VoucherDTO> getAllVouchers() {
        List<VoucherE> voucherEList = voucherRepository.findAll();
        return voucherEList.stream()
                .map(VoucherM::convertVoucherEToVoucherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VoucherDTO> getVoucherById(Integer id) {
        Optional<VoucherE> VoucherEOptional = voucherRepository.findById(id);
        return VoucherEOptional.map(VoucherM::convertVoucherEToVoucherDTO);
    }

    @Override
    public Optional<VoucherDTO> getVoucherByCodeVoucher(String code) {
        Optional<VoucherE> VoucherEOptional = voucherRepository.findByCodeVoucher(code);
        return VoucherEOptional.map(VoucherM::convertVoucherEToVoucherDTO);
    }

    @Override
    public Optional<VoucherM> updateVoucher(Integer id, VoucherDTO voucherDTO) {
        return voucherRepository.findById(id)
                .map(tempDis -> {
                    tempDis.setCodeVoucher(voucherDTO.getCodeVoucher());
                    tempDis.setVoucherName(voucherDTO.getVoucherName());
                    tempDis.setVoucherPercentage(voucherDTO.getVoucherPercentage());
                    tempDis.setStartDate(voucherDTO.getStartDate());
                    tempDis.setEndDate(voucherDTO.getEndDate());
                    tempDis.setDescription(voucherDTO.getDescription());
                    voucherRepository.save(tempDis);
                    return VoucherM.convertVoucherEToVoucherM(tempDis);
                });
    }

    @Override
    public boolean deleteVoucher(Integer id) {
        return voucherRepository.findById(id)
                .map(Voucher -> {
                    voucherRepository.delete(Voucher);
                    return true;
                }).orElse(false);
    }
}
