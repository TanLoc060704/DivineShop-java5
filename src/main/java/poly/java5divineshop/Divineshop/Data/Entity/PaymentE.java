package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment")
public class PaymentE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_payment")
    private Integer id;

    @Column(name = "thoi_gian")
    private LocalDate thoiGian;

    @Column(name = "mota", columnDefinition = "nvarchar(255)")
    private String mota;

    @Column(name = "sotien")
    private Float soTien;

    @Column(name = "trangthai")
    private Boolean trangThai;

    @Column(name = "tenuser", nullable = false)
    private String tenUser;
}
