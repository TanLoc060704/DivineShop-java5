package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Discount")
public class DiscountE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_discount")
    private Integer sysIdDiscount;

    @Column(name = "code_discount", unique = true, nullable = false)
    private String codeDiscount;

    @Column(name = "ten_giam_gia", columnDefinition = "nvarchar(255)")
    private String discountName;

    @Column(name = "percent_giam_gia")
    private Float discountPercentage;

    @Column(name = "ngay_bat_dau")
    private Date startDate;

    @Column(name = "ngay_ket_thuc")
    private Date endDate;

    @Column(name = "mota", columnDefinition = "nvarchar(255)")
    private String description;
}