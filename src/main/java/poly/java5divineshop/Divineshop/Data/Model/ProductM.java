package poly.java5divineshop.Divineshop.Data.Model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import poly.java5divineshop.Divineshop.Data.Dto.ProductDTO;
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
    private Float giaSanPham;
    private Float percentGiamGia;
    private String anhSanPham;
    private String slug;
    private String danhMuc;
    private String mota;
    private boolean activeSanPham;
    private Integer sysIdDiscount;
    private Integer soLuong;
    private Integer soLuongMua;
    private Integer soLuotThich;
    private List<CategoryM> categories;

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
                .sysIdDiscount(productE.getSysIdDiscount())
                .soLuong(productE.getSoLuong())
                .soLuongMua(productE.getSoLuongMua())
                .soLuotThich(productE.getSoLuotThich())
                .categories(CategoryM.convertListCategoryEToListCategoryM(productE.getCategories()))
                .build();
    }

    public static ProductM convertProductDTOToProductM(ProductDTO productDTO) {
        return ProductM.builder()
                .id(productDTO.getId())
                .maSanPham(productDTO.getMaSanPham())
                .tenSanPham(productDTO.getTenSanPham())
                .tinhTrang(productDTO.isTinhTrang())
                .theLoai(productDTO.getTheLoai())
                .giaSanPham(productDTO.getGiaSanPham())
                .percentGiamGia(productDTO.getPercentGiamGia())
                .anhSanPham(productDTO.getAnhSanPham())
                .slug(productDTO.getSlug())
                .danhMuc(productDTO.getDanhMuc())
                .mota(productDTO.getMota())
                .activeSanPham(productDTO.isActiveSanPham())
                .sysIdDiscount(productDTO.getSysIdDiscount())
                .soLuong(productDTO.getSoLuong())
                .soLuongMua(productDTO.getSoLuongMua())
                .soLuotThich(productDTO.getSoLuotThich())
                .categories(CategoryM.convertListCategoryDTOToListCategoryM(productDTO.getCategories()))
                .build();
    }

    public static ProductDTO convertProductMToProductDTO(ProductM productM) {
        return ProductDTO.builder()
                .id(productM.getId())
                .maSanPham(productM.getMaSanPham())
                .tenSanPham(productM.getTenSanPham())
                .tinhTrang(productM.isTinhTrang())
                .theLoai(productM.getTheLoai())
                .giaSanPham(productM.getGiaSanPham())
                .percentGiamGia(productM.getPercentGiamGia())
                .anhSanPham(productM.getAnhSanPham())
                .slug(productM.getSlug())
                .danhMuc(productM.getDanhMuc())
                .mota(productM.getMota())
                .activeSanPham(productM.isActiveSanPham())
                .sysIdDiscount(productM.getSysIdDiscount())
                .soLuong(productM.getSoLuong())
                .soLuongMua(productM.getSoLuongMua())
                .soLuotThich(productM.getSoLuotThich())
                .categories(CategoryM.convertListCategoryMToListCategoryDTO(productM.getCategories()))
                .build();
    }

    public static ProductE convertProductMToProductE(ProductM productM) {
        return ProductE.builder()
                .id(productM.getId())
                .maSanPham(productM.getMaSanPham())
                .tenSanPham(productM.getTenSanPham())
                .tinhTrang(productM.isTinhTrang())
                .theLoai(productM.getTheLoai())
                .giaSanPham(productM.getGiaSanPham())
                .percentGiamGia(productM.getPercentGiamGia())
                .anhSanPham(productM.getAnhSanPham())
                .slug(productM.getSlug())
                .danhMuc(productM.getDanhMuc())
                .mota(productM.getMota())
                .activeSanPham(productM.isActiveSanPham())
                .sysIdDiscount(productM.getSysIdDiscount())
                .soLuong(productM.getSoLuong())
                .soLuongMua(productM.getSoLuongMua())
                .soLuotThich(productM.getSoLuotThich())
                .categories(CategoryM.convertListCategoryMToListCategoryE(productM.getCategories()))
                .build();
    }

    public static List<ProductM> convertListProductEToListProductM(List<ProductE> productEList) {
        return productEList.stream()
                .map(ProductM::convertProductEToProductM)
                .collect(Collectors.toList());
    }

    public static List<ProductM> convertListProductDTOToListProductM(List<ProductDTO> productDTOList) {
        return productDTOList.stream()
                .map(ProductM::convertProductDTOToProductM)
                .collect(Collectors.toList());
    }

    public static List<ProductDTO> convertListProductMToListProductDTO(List<ProductM> productMList) {
        return productMList.stream()
                .map(ProductM::convertProductMToProductDTO)
                .collect(Collectors.toList());
    }

    public static List<ProductE> convertListProductMToListProductE(List<ProductM> productMList) {
        return productMList.stream()
                .map(ProductM::convertProductMToProductE)
                .collect(Collectors.toList());
    }

    public static Page<ProductM> convertPageProductEToPageProductM(Page<ProductE> pageProductE) {
        List<ProductM> productMList = convertListProductEToListProductM(pageProductE.getContent());
        return new PageImpl<>(productMList, pageProductE.getPageable(), pageProductE.getTotalElements());
    }
}
