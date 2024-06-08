package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Voucher")
public class VoucherE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_Voucher")
    private Integer sysIdVoucher;

    @Column(name = "code_Voucher", unique = true, nullable = false)
    private String codeVoucher;

    @Column(name = "ten_giam_gia", columnDefinition = "nvarchar(255)")
    private String VoucherName;

    @Column(name = "percent_giam_gia")
    private Float VoucherPercentage;

    @Column(name = "ngay_bat_dau")
    private Date startDate;

    @Column(name = "ngay_ket_thuc")
    private Date endDate;

    @Column(name = "mota", columnDefinition = "nvarchar(255)")
    private String description;
}