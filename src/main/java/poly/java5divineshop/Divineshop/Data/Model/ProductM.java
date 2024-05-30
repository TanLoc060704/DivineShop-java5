package poly.java5divineshop.Divineshop.Data.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductM {

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

    public static ProductM convertProductEToProductM(ProductE productE) {
        return ProductM.builder()
                .id(productE.getId())
                .maSanPham(productE.getMaSanPham())
                .tenSanPham(productE.getTenSanPham())
                .tinhTrang(productE.isTinhTrang())
                .theLoai(productE.getTheLoai())
                .giaSanPham(productE.getGiaSanPham())
                .percentGiamGia(productE.getPercentGiamGia())
                .anhSanPham(productE.getAnhSanPham())
                .slug(productE.getSlug())
                .danhMuc(productE.getDanhMuc())
                .mota(productE.getMota())
                .activeSanPham(productE.isActiveSanPham())
                .build();
    }

    public static List<ProductM> convertListProductEToListProductM(List<ProductE> productEList) {
        return productEList.stream()
                .map(ProductM::convertProductEToProductM)
                .collect(Collectors.toList());
    }

}
