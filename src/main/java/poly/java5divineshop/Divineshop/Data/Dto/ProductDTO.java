package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private int id;
    private String maSanPham;
    private String tenSanPham;
    private boolean tinhTrang;
    private String theLoai;
    private String giaSanPham;
    private String percentGiamGia;
    private String anhSanPham;
    private String slug;
    private String danhMuc;
    private String mota;
    private boolean activeSanPham;
}
