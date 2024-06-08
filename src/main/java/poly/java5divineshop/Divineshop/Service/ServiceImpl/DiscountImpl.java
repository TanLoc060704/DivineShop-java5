package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.DiscountDTO;
import poly.java5divineshop.Divineshop.Data.Entity.DiscountE;
import poly.java5divineshop.Divineshop.Data.Model.DiscountM;
import poly.java5divineshop.Divineshop.Repo.DiscountRepo;
import poly.java5divineshop.Divineshop.Service.DiscountService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountImpl implements DiscountService {

    private final DiscountRepo discountRepository;

    @Autowired
    public DiscountImpl(DiscountRepo discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public DiscountDTO createDiscount(DiscountDTO discountDTO) {
        DiscountE discountE = DiscountM.convertDiscountDTOToDiscountE(discountDTO);
        DiscountE savedDiscountE = discountRepository.save(discountE);
        return DiscountM.convertDiscountEToDiscountDTO(savedDiscountE);
    }

    @Override
    public List<DiscountDTO> getAllDiscounts() {
        List<DiscountE> discountEList = discountRepository.findAll();
        return discountEList.stream()
                .map(DiscountM::convertDiscountEToDiscountDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DiscountDTO> getDiscountById(Integer id) {
        Optional<DiscountE> discountEOptional = discountRepository.findById(id);
        return discountEOptional.map(DiscountM::convertDiscountEToDiscountDTO);
    }

    @Override
    public Optional<DiscountDTO> getDiscountByCodeDiscount(String code) {
        Optional<DiscountE> discountEOptional = discountRepository.findByCodeDiscount(code);
        return discountEOptional.map(DiscountM::convertDiscountEToDiscountDTO);
    }

    @Override
    public Optional<DiscountM> updateDiscount(Integer id, DiscountDTO discountDTO) {
        return discountRepository.findById(id)
                .map(tempDis -> {
                    tempDis.setCodeDiscount(discountDTO.getCodeDiscount());
                    tempDis.setDiscountName(discountDTO.getDiscountName());
                    tempDis.setDiscountPercentage(discountDTO.getDiscountPercentage());
                    tempDis.setStartDate(discountDTO.getStartDate());
                    tempDis.setEndDate(discountDTO.getEndDate());
                    tempDis.setDescription(discountDTO.getDescription());
                    discountRepository.save(tempDis);
                    return DiscountM.convertDiscountEToDiscountM(tempDis);
                });
    }

    @Override
    public boolean deleteDiscount(Integer id) {
        return discountRepository.findById(id)
                .map(discount -> {
                    discountRepository.delete(discount);
                    return true;
                }).orElse(false);
    }
}
