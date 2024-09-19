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
    private LocalDate ngaySuDung;

    // Optionally, you can add relationships to UserE and VoucherE entities
    // if you want to establish proper foreign key relationships
    // Uncomment the below relationships if needed

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sys_id_user", insertable = false, updatable = false)
    private UserE user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sys_id_voucher", insertable = false, updatable = false)
    private VoucherE voucher;
    */
}
