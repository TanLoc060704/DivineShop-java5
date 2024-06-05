package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "ma_san_pham", columnDefinition = "nvarchar(255)")
    private String maSanPham;

    @Column(name = "ten_san_pham", columnDefinition = "nvarchar(255)")
    private String tenSanPham;

    @Column(name = "tinh_trang")
    private boolean tinhTrang;

    @Column(name = "the_loai", columnDefinition = "nvarchar(255)")
    private String theLoai;

    @Column(name = "gia_san_pham")
    private Float giaSanPham;

    @Column(name = "percent_giam_gia")
    private Float percentGiamGia;

    @Column(name = "anh_san_pham", columnDefinition = "nvarchar(255)")
    private String anhSanPham;

    @Column(name = "slug", columnDefinition = "nvarchar(255)", unique = true, nullable = false)
    private String slug;

    @Column(name = "danh_muc", columnDefinition = "nvarchar(255)")
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "CategoryDetail",
            joinColumns = @JoinColumn(name = "Sys_id_product"),
            inverseJoinColumns = @JoinColumn(name = "Sys_id_category"))
    private List<CategoryE> categories;


    public void addCateList(CategoryE categoryE) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(categoryE);
    }
}
