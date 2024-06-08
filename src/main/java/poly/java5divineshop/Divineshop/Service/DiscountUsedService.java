package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.DiscountDTO;
import poly.java5divineshop.Divineshop.Data.Dto.DiscountUsedDTO;

import java.util.Optional;

public interface DiscountUsedService {
    Optional<DiscountUsedDTO> getDiscountUsedByUserIDAndDiscountID(Integer userId, Integer discountId);
    DiscountUsedDTO createDiscountUsed(DiscountUsedDTO discountUsedDTO);
}
