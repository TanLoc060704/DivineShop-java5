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
@Table(name = "voucher")
public class VoucherE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_Voucher")
    private Integer sysIdVoucher;

    @Column(name = "code_Voucher", unique = true, nullable = false, length = 8)
    private String codeVoucher;

    @Column(name = "ten_giam_gia", length = 255)
    private String voucherName;

    @Column(name = "percent_giam_gia")
    private Float voucherPercentage;

    @Column(name = "ngay_bat_dau")
    private LocalDate startDate;

    @Column(name = "ngay_ket_thuc")
    private LocalDate endDate;

    @Column(name = "mota", length = 255)
    private String description;
}
