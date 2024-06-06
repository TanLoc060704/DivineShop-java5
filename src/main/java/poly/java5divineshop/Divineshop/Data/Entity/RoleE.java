package poly.java5divineshop.Divineshop.Data.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class RoleE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer idRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    @JsonIgnore
    private AccountE account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username_user", referencedColumnName = "ten_dang_nhap")
    @JsonIgnore
    private UserE user;

    @Column(name = "username", insertable = false, updatable = false, nullable = false)
    private String username;

    @Column(name = "username_user", insertable = false, updatable = false, nullable = false)
    private String usernameUser;

    @Column(name = "role", nullable = false)
    private String role;

}
