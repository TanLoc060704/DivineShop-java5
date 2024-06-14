package poly.java5divineshop.Divineshop.Data.Model;

import lombok.*;
import poly.java5divineshop.Divineshop.Data.Dto.VoucherUsedDTO;
import poly.java5divineshop.Divineshop.Data.Entity.VoucherUsedE;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherUsedM {
    private Integer sysIdVoucherUsed;
    private Integer sysIdUser;
    private Integer sysIdVoucher;
    private Date ngaySuDung;


    public static VoucherUsedM convertVoucherUsedEToVoucherUsedM(VoucherUsedE voucherUsedE) {
        return VoucherUsedM.builder()
                .sysIdVoucherUsed(voucherUsedE.getSysIdVoucherUsed())
                .sysIdUser(voucherUsedE.getSysIdUser())
                .sysIdVoucher(voucherUsedE.getSysIdVoucher())
                .ngaySuDung(voucherUsedE.getNgaySuDung())
                .build();
    }

    public static VoucherUsedE convertVoucherUsedDTOToVoucherUsedE(VoucherUsedDTO voucherUsedDTO) {
        return VoucherUsedE.builder()
                .sysIdVoucherUsed(voucherUsedDTO.getSysIdVoucherUsed())
                .sysIdUser(voucherUsedDTO.getSysIdUser())
                .sysIdVoucher(voucherUsedDTO.getSysIdVoucher())
                .ngaySuDung(voucherUsedDTO.getNgaySuDung())
                .build();
    }

    public static VoucherUsedDTO convertVoucherUsedEToVoucherUsedDTO(VoucherUsedE voucherUsedE) {
        return VoucherUsedDTO.builder()
                .sysIdVoucherUsed(voucherUsedE.getSysIdVoucherUsed())
                .sysIdUser(voucherUsedE.getSysIdUser())
                .sysIdVoucher(voucherUsedE.getSysIdVoucher())
                .ngaySuDung(voucherUsedE.getNgaySuDung())
                .build();
    }
}
