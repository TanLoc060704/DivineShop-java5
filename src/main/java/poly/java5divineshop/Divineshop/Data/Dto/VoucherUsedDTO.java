package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherUsedDTO {
    private Integer sysIdVoucherUsed;
    private Integer sysIdUser;
    private Integer sysIdVoucher;
    private Date ngaySuDung;
}
