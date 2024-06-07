package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "[User]")
public class UserE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sys_id_user")
    private Integer sysIdUser;

    @Column(name = "ten_dang_nhap", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "ho_va_ten")
    private String hoVaTen;

    @Column(name = "so_du")
    private String soDu;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_tham_gia")
    private Date ngayThamGia;

    @Column(name = "anh_dai_dien")
    private String anhDaiDien;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoleE> roles;
}
