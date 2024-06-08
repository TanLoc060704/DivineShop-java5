package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountDTO {

    private Integer sysIdDiscount;
    private String codeDiscount;
    private String discountName;
    private Float discountPercentage;
    private Date startDate;
    private Date endDate;
    private String description;

}
