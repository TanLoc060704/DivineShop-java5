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
public class VoucherDTO {

    private Integer sysIdVoucher;
    private String codeVoucher;
    private String VoucherName;
    private Float VoucherPercentage;
    private Date startDate;
    private Date endDate;
    private String description;

}
