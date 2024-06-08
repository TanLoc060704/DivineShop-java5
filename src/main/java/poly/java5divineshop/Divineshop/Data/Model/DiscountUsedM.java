package poly.java5divineshop.Divineshop.Data.Model;

import jakarta.persistence.*;
import lombok.*;
import poly.java5divineshop.Divineshop.Data.Dto.DiscountDTO;
import poly.java5divineshop.Divineshop.Data.Dto.DiscountUsedDTO;
import poly.java5divineshop.Divineshop.Data.Entity.DiscountE;
import poly.java5divineshop.Divineshop.Data.Entity.DiscountUsedE;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountUsedM {
    private Integer sysIdDiscountUsed;
    private Integer sysIdUser;
    private Integer sysIdDiscount;
    private Date ngaySuDung;


    public static DiscountUsedM convertDiscountUsedEToDiscountUsedM(DiscountUsedE discountUsedE) {
        return DiscountUsedM.builder()
                .sysIdDiscountUsed(discountUsedE.getSysIdDiscountUsed())
                .sysIdUser(discountUsedE.getSysIdUser())
                .sysIdDiscount(discountUsedE.getSysIdDiscount())
                .ngaySuDung(discountUsedE.getNgaySuDung())
                .build();
    }

    public static DiscountUsedE convertDiscountUsedDTOToDiscountUsedE(DiscountUsedDTO discountUsedDTO) {
        return DiscountUsedE.builder()
                .sysIdDiscountUsed(discountUsedDTO.getSysIdDiscountUsed())
                .sysIdUser(discountUsedDTO.getSysIdUser())
                .sysIdDiscount(discountUsedDTO.getSysIdDiscount())
                .ngaySuDung(discountUsedDTO.getNgaySuDung())
                .build();
    }

    public static DiscountUsedDTO convertDiscountUsedEToDiscountUsedDTO(DiscountUsedE discountUsedE) {
        return DiscountUsedDTO.builder()
                .sysIdDiscountUsed(discountUsedE.getSysIdDiscountUsed())
                .sysIdUser(discountUsedE.getSysIdUser())
                .sysIdDiscount(discountUsedE.getSysIdDiscount())
                .ngaySuDung(discountUsedE.getNgaySuDung())
                .build();
    }
}
