package poly.java5divineshop.Divineshop.Data.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.java5divineshop.Divineshop.Data.Dto.VoucherDTO;
import poly.java5divineshop.Divineshop.Data.Entity.VoucherE;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherM {

    private Integer sysIdVoucher;
    private String codeVoucher;
    private String VoucherName;
    private Float VoucherPercentage;
    private Date startDate;
    private Date endDate;
    private String description;

    public static VoucherM convertVoucherEToVoucherM(VoucherE voucherE) {
        return VoucherM.builder()
                .sysIdVoucher(voucherE.getSysIdVoucher())
                .codeVoucher(voucherE.getCodeVoucher())
                .VoucherName(voucherE.getVoucherName())
                .VoucherPercentage(voucherE.getVoucherPercentage())
                .startDate(voucherE.getStartDate()) // Kiểu Date
                .endDate(voucherE.getEndDate()) // Kiểu Date
                .description(voucherE.getDescription())
                .build();
    }

    public static VoucherE convertVoucherMToVoucherE(VoucherM voucherM) {
        return VoucherE.builder()
                .sysIdVoucher(voucherM.getSysIdVoucher())
                .codeVoucher(voucherM.getCodeVoucher())
                .VoucherName(voucherM.getVoucherName())
                .VoucherPercentage(voucherM.getVoucherPercentage())
                .startDate(voucherM.getStartDate())
                .endDate(voucherM.getEndDate())
                .description(voucherM.getDescription())
                .build();
    }

    public static VoucherDTO convertVoucherMToVoucherDTO(VoucherM voucherM) {
        return VoucherDTO.builder()
                .sysIdVoucher(voucherM.getSysIdVoucher())
                .codeVoucher(voucherM.getCodeVoucher())
                .VoucherName(voucherM.getVoucherName())
                .VoucherPercentage(voucherM.getVoucherPercentage())
                .startDate(voucherM.getStartDate())
                .endDate(voucherM.getEndDate())
                .description(voucherM.getDescription())
                .build();
    }

    public static VoucherM convertVoucherDTOToVoucherM(VoucherDTO voucherDTO) {
        return VoucherM.builder()
                .sysIdVoucher(voucherDTO.getSysIdVoucher())
                .codeVoucher(voucherDTO.getCodeVoucher())
                .VoucherName(voucherDTO.getVoucherName())
                .VoucherPercentage(voucherDTO.getVoucherPercentage())
                .startDate(voucherDTO.getStartDate())
                .endDate(voucherDTO.getEndDate())
                .description(voucherDTO.getDescription())
                .build();
    }

    public static VoucherE convertVoucherDTOToVoucherE(VoucherDTO voucherDTO) {
        return VoucherE.builder()
                .sysIdVoucher(voucherDTO.getSysIdVoucher())
                .codeVoucher(voucherDTO.getCodeVoucher())
                .VoucherName(voucherDTO.getVoucherName())
                .VoucherPercentage(voucherDTO.getVoucherPercentage())
                .startDate(voucherDTO.getStartDate())
                .endDate(voucherDTO.getEndDate())
                .description(voucherDTO.getDescription())
                .build();
    }

    public static VoucherDTO convertVoucherEToVoucherDTO(VoucherE voucherE) {
        return VoucherDTO.builder()
                .sysIdVoucher(voucherE.getSysIdVoucher())
                .codeVoucher(voucherE.getCodeVoucher())
                .VoucherName(voucherE.getVoucherName())
                .VoucherPercentage(voucherE.getVoucherPercentage())
                .startDate(voucherE.getStartDate())
                .endDate(voucherE.getEndDate())
                .description(voucherE.getDescription())
                .build();
    }

}
