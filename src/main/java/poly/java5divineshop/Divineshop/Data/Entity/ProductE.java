package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Product")
public class ProductE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_product")
    private int id;

    @Column(name = "ma_san_pham", columnDefinition = "nvarchar(max)")
    private String maSanPham;

    @Column(name = "ten_san_pham", columnDefinition = "nvarchar(max)")
    private String tenSanPham;

    @Column(name = "tinh_trang")
    private boolean tinhTrang;

    @Column(name = "the_loai", columnDefinition = "nvarchar(max)")
    private String theLoai;

    @Column(name = "gia_san_pham", columnDefinition = "nvarchar(max)")
    private String giaSanPham;

    @Column(name = "percent_giam_gia", columnDefinition = "nvarchar(max)")
    private String percentGiamGia;

    @Column(name = "anh_san_pham", columnDefinition = "nvarchar(max)")
    private String anhSanPham;

    @Column(name = "slug", columnDefinition = "nvarchar(max)")
    private String slug;

    @Column(name = "danh_muc", columnDefinition = "nvarchar(max)")
    private String danhMuc;

    @Column(name = "mota", columnDefinition = "nvarchar(max)")
    private String mota;

    @Column(name = "active_san_pham")
    private boolean activeSanPham = true;

    @Column(name = "Sys_id_discount")
    private Integer sysIdDiscount;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "soluongmua")
    private Integer soLuongMua;

    @Column(name = "soluotthich")
    private Integer soLuotThich;
}
