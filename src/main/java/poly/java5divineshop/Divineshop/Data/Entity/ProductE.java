package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class ProductE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_product")
    private int id;

    @Column(name = "ma_san_pham", length = 255)
    private String maSanPham;

    @Column(name = "ten_san_pham", length = 255)
    private String tenSanPham;

    @Column(name = "tinh_trang")
    private boolean tinhTrang;

    @Column(name = "the_loai", length = 255)
    private String theLoai;

    @Column(name = "gia_san_pham")
    private Float giaSanPham;

    @Column(name = "percent_giam_gia")
    private Float percentGiamGia;

    @Column(name = "anh_san_pham", length = 255)
    private String anhSanPham;

    @Column(name = "slug", length = 255, unique = true, nullable = false)
    private String slug;

    @Column(name = "danh_muc", length = 255)
    private String danhMuc;

    @Lob
    @Column(name = "mota")
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "category_detail",
            joinColumns = @JoinColumn(name = "Sys_id_product"),
            inverseJoinColumns = @JoinColumn(name = "Sys_id_category"))
    private List<CategoryE> categories = new ArrayList<>();

    public void addCateList(CategoryE categoryE) {
        categories.add(categoryE);
    }
}
