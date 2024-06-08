package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.DiscountUsedDTO;
import poly.java5divineshop.Divineshop.Data.Entity.DiscountE;
import poly.java5divineshop.Divineshop.Data.Entity.DiscountUsedE;
import poly.java5divineshop.Divineshop.Data.Model.DiscountM;
import poly.java5divineshop.Divineshop.Data.Model.DiscountUsedM;
import poly.java5divineshop.Divineshop.Repo.DiscountUsedRepo;
import poly.java5divineshop.Divineshop.Service.DiscountUsedService;

import java.util.Date;
import java.util.Optional;

@Service
public class DiscountUsedImpl implements DiscountUsedService {
    @Autowired
    private DiscountUsedRepo discountUsedRepository;


    @Override
    public Optional<DiscountUsedDTO> getDiscountUsedByUserIDAndDiscountID(Integer userId, Integer discountId) {
        return discountUsedRepository.findBySysIdUserAndSysIdDiscount(userId, discountId)
                .map(DiscountUsedM::convertDiscountUsedEToDiscountUsedDTO);
    }

    @Override
    public DiscountUsedDTO createDiscountUsed(DiscountUsedDTO discountUsedDTO) {
        DiscountUsedE discountUsedE = DiscountUsedM.convertDiscountUsedDTOToDiscountUsedE(discountUsedDTO);
        discountUsedE.setNgaySuDung(new Date());
        DiscountUsedE savedDiscountUsedE = discountUsedRepository.save(discountUsedE);
        return DiscountUsedM.convertDiscountUsedEToDiscountUsedDTO(savedDiscountUsedE);
    }
}
