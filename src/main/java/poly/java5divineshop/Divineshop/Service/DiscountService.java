package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.DiscountDTO;
import poly.java5divineshop.Divineshop.Data.Model.DiscountM;

import java.util.List;
import java.util.Optional;

public interface DiscountService {
    List<DiscountDTO> getAllDiscounts();

    Optional<DiscountDTO> getDiscountById(Integer id);

    Optional<DiscountDTO> getDiscountByCodeDiscount(String code);

    DiscountDTO createDiscount(DiscountDTO discountDTO);

    Optional<DiscountM> updateDiscount(Integer id, DiscountDTO discountDTO);

    boolean deleteDiscount(Integer id);
}
