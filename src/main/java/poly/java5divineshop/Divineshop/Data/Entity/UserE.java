package poly.java5divineshop.Divineshop.Data.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "[user]")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_user")
    private Integer Id;

    @Column(name = "ten_dang_nhap")
    private String ten_dang_nhap;

    @Column(name = "email")
    private String email;

    @Column(name = "ho_va_ten")
    private String ho_va_ten;

    @Column(name = "so_du")
    private String so_du;

    @Column(name = "ngay_tham_gia")
    private Date ngay_tham_gia;

    @Column(name = "anh_dai_dien")
    private String anh_dai_dien;


}
