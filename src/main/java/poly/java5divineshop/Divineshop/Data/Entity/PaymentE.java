package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "[Payment]")
public class PaymentE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_payment")
    private Integer id;

    @Column(name = "thoi_gian")
    @Temporal(TemporalType.DATE)
    private Date thoigian;

    @Column(name = "mota")
    private String mota;

    @Column(name = "sotien")
    private Float sotien;

    @Column(name = "trangthai")
    private Boolean trangthai;

    @Column(name = "tenuser", nullable = false)
    private String tenuser;
}
