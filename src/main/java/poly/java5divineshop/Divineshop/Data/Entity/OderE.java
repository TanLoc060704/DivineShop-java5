package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order")
public class OderE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_cart_payment")
    private Integer id;

    @Column(name = "ma_don_hang", length = 255, nullable = false, unique = true)
    private String maDonHang;

    @Column(name = "ngay_lap_don")
    private LocalDate ngayLapDon;

    @Column(name = "trang_thai_thanh_toan")
    private Boolean trangThaiThanhToan;

    @Column(name = "tong_tien_san_pham")
    private Double tongTienThanhToan;

    @Column(name = "tien_thanh_toan")
    private Double tienThanhToan;

    @Column(name = "so_luong_mua")
    private Integer soLuongMua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sys_id_product", referencedColumnName = "Sys_id_product")
    private ProductE productE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sys_id_user", referencedColumnName = "sys_id_user")
    private UserE userE;

}
