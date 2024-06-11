package poly.java5divineshop.Divineshop.Data.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "[Order]")
public class OderE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_cart_payment")
    private Integer id;

    @Column(name = "ma_don_hang")
    private String maDonHang;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_lap_don")
    private Date ngayLapDon;

    @Column(name = "trang_thai_thanh_toan")
    private Boolean trangThaiThanhToan;

    @Column(name = "tong_tien_san_pham")
    private Double tongTienThanhToan;

    @Column(name = "tien_thanh_toan")
    private Double tienThanhToan;

    @Column(name = "so_luong_mua")
    private Integer soLuongMua;

    @ManyToOne
    @JoinColumn(name = "Sys_id_product", referencedColumnName = "Sys_id_product")
    private ProductE productE;

    @ManyToOne
    @JoinColumn(name = "Sys_id_user", referencedColumnName = "Sys_id_user")
    private UserE userE;
}
