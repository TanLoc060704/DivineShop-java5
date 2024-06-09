package poly.java5divineshop.Divineshop.Data.Model;

import jdk.jshell.Snippet;
import lombok.*;
import poly.java5divineshop.Divineshop.Data.Dto.OderDTO;
import poly.java5divineshop.Divineshop.Data.Entity.OderE;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OderM {
    private Integer id;
    private String maDonHang;
    private Date ngayLapDon;
    private Boolean trangThaiThanhToan;
    private Double tongTienThanhToan;
    private Double tienThanhToan;
    private Integer soLuongMua;
    private ProductE productE;
    private UserE userE;

    public static OderM convertOderEToOderM(OderE oderE) {
        return OderM.builder()
                .id(oderE.getId())
                .maDonHang(oderE.getMaDonHang())
                .ngayLapDon(oderE.getNgayLapDon())
                .trangThaiThanhToan(oderE.getTrangThaiThanhToan())
                .tongTienThanhToan(oderE.getTongTienThanhToan())
                .tienThanhToan(oderE.getTienThanhToan())
                .soLuongMua(oderE.getSoLuongMua())
                .productE(oderE.getProductE())
                .userE(oderE.getUserE())
                .build();
    }

    public static List<OderM> convertListOderEToListOderM(List<OderE> listOderE) {
        return listOderE.stream()
                .map(e -> convertOderEToOderM(e))
                .collect(Collectors.toList());
    }
}
