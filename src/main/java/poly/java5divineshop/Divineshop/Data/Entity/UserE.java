package poly.java5divineshop.Divineshop.Data.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sys_id_user")
    private Integer sysIdUser;

    @Column(name = "ten_dang_nhap", nullable = false, unique = true, length = 50)
    private String tenDangNhap;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "ho_va_ten", length = 255)
    private String hoVaTen;

    @Column(name = "so_du", length = 255)
    private String soDu;

    @Column(name = "ngay_tham_gia")
    private LocalDate ngayThamGia;

    @Column(name = "anh_dai_dien", length = 255)
    private String anhDaiDien;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RoleE> roles;
}
