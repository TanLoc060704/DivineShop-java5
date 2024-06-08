package poly.java5divineshop.Divineshop.Data.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.java5divineshop.Divineshop.Data.Dto.DiscountDTO;
import poly.java5divineshop.Divineshop.Data.Entity.DiscountE;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountM {

    private Integer sysIdDiscount;
    private String codeDiscount;
    private String discountName;
    private Float discountPercentage;
    private Date startDate;
    private Date endDate;
    private String description;

    public static DiscountM convertDiscountEToDiscountM(DiscountE discountE) {
        return DiscountM.builder()
                .sysIdDiscount(discountE.getSysIdDiscount())
                .codeDiscount(discountE.getCodeDiscount())
                .discountName(discountE.getDiscountName())
                .discountPercentage(discountE.getDiscountPercentage())
                .startDate(discountE.getStartDate()) // Kiểu Date
                .endDate(discountE.getEndDate()) // Kiểu Date
                .description(discountE.getDescription())
                .build();
    }

    public static DiscountE convertDiscountMToDiscountE(DiscountM discountM) {
        return DiscountE.builder()
                .sysIdDiscount(discountM.getSysIdDiscount())
                .codeDiscount(discountM.getCodeDiscount())
                .discountName(discountM.getDiscountName())
                .discountPercentage(discountM.getDiscountPercentage())
                .startDate(discountM.getStartDate())
                .endDate(discountM.getEndDate())
                .description(discountM.getDescription())
                .build();
    }

    public static DiscountDTO convertDiscountMToDiscountDTO(DiscountM discountM) {
        return DiscountDTO.builder()
                .sysIdDiscount(discountM.getSysIdDiscount())
                .codeDiscount(discountM.getCodeDiscount())
                .discountName(discountM.getDiscountName())
                .discountPercentage(discountM.getDiscountPercentage())
                .startDate(discountM.getStartDate())
                .endDate(discountM.getEndDate())
                .description(discountM.getDescription())
                .build();
    }

    public static DiscountM convertDiscountDTOToDiscountM(DiscountDTO discountDTO) {
        return DiscountM.builder()
                .sysIdDiscount(discountDTO.getSysIdDiscount())
                .codeDiscount(discountDTO.getCodeDiscount())
                .discountName(discountDTO.getDiscountName())
                .discountPercentage(discountDTO.getDiscountPercentage())
                .startDate(discountDTO.getStartDate())
                .endDate(discountDTO.getEndDate())
                .description(discountDTO.getDescription())
                .build();
    }

    public static DiscountE convertDiscountDTOToDiscountE(DiscountDTO discountDTO) {
        return DiscountE.builder()
                .sysIdDiscount(discountDTO.getSysIdDiscount())
                .codeDiscount(discountDTO.getCodeDiscount())
                .discountName(discountDTO.getDiscountName())
                .discountPercentage(discountDTO.getDiscountPercentage())
                .startDate(discountDTO.getStartDate())
                .endDate(discountDTO.getEndDate())
                .description(discountDTO.getDescription())
                .build();
    }

    public static DiscountDTO convertDiscountEToDiscountDTO(DiscountE discountE) {
        return DiscountDTO.builder()
                .sysIdDiscount(discountE.getSysIdDiscount())
                .codeDiscount(discountE.getCodeDiscount())
                .discountName(discountE.getDiscountName())
                .discountPercentage(discountE.getDiscountPercentage())
                .startDate(discountE.getStartDate())
                .endDate(discountE.getEndDate())
                .description(discountE.getDescription())
                .build();
    }

}
