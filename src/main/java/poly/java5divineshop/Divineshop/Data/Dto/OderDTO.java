package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poly.java5divineshop.Divineshop.Data.Entity.OderE;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OderDTO {
    private Integer id;
    private String maDonHang;
    private LocalDate ngayLapDon;
    private Boolean trangThaiThanhToan;
    private Double tongTienThanhToan;
    private Double tienThanhToan;
    private Integer soLuongMua;
    private ProductE productE;
    private UserE userE;
    private List<ProductE> listProduct;

    public static OderE convertOderDtoToOderE(OderDTO oderDTO) {
        return OderE.builder()
                .id(oderDTO.getId())
                .maDonHang(oderDTO.getMaDonHang())
                .ngayLapDon(oderDTO.getNgayLapDon())
                .trangThaiThanhToan(oderDTO.getTrangThaiThanhToan())
                .tongTienThanhToan(oderDTO.getTongTienThanhToan())
                .tienThanhToan(oderDTO.getTienThanhToan())
                .soLuongMua(oderDTO.getSoLuongMua())
                .productE(oderDTO.getProductE())
                .userE(oderDTO.getUserE())
                .build();
    }

    public static List<OderE> convertListOderDtoToListOderEE(List<OderDTO> listOderDTO) {
        return listOderDTO.stream()
                .map(e -> convertOderDtoToOderE(e))
                .collect(Collectors.toList());
    }
}
