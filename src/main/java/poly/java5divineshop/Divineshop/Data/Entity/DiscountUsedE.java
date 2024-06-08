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
@Table(name = "discount_used")
public class DiscountUsedE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_discount_used")
    private Integer sysIdDiscountUsed;

    @Column(name = "Sys_id_user", nullable = false)
    private Integer sysIdUser;

    @Column(name = "Sys_id_discount", nullable = false)
    private Integer sysIdDiscount;

    @Column(name = "ngay_su_dung", nullable = false)
    private Date ngaySuDung;

}
