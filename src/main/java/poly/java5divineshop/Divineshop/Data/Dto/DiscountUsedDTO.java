package poly.java5divineshop.Divineshop.Data.Dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountUsedDTO {
    private Integer sysIdDiscountUsed;
    private Integer sysIdUser;
    private Integer sysIdDiscount;
    private Date ngaySuDung;
}
