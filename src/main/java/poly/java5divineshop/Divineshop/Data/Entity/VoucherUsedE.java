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
@Table(name = "voucher_used")
public class VoucherUsedE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_voucher_used")
    private Integer sysIdVoucherUsed;

    @Column(name = "Sys_id_user", nullable = false)
    private Integer sysIdUser;

    @Column(name = "Sys_id_voucher", nullable = false)
    private Integer sysIdVoucher;

    @Column(name = "ngay_su_dung", nullable = false)
    private Date ngaySuDung;

}
