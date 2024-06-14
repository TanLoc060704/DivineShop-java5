package poly.java5divineshop.Divineshop.Data.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "[Reply]")
public class ReplyE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_reply")
    private Integer sysIdReply;

    @Column(name = "noi_dung", columnDefinition = "nvarchar(MAX)", nullable = false)
    private String noiDung;

    @Column(name = "ngay_tra_loi", nullable = false)
    private Date ngayTraLoi = new Date();

    @ManyToOne
    @JoinColumn(name = "Sys_id_user", nullable = false)
    private UserE user;

    @ManyToOne()
    @JoinColumn(name = "Sys_id_comment")
    @JsonIgnore
    private CommentE comment;

    public ReplyE(String noiDung, Date ngayTraLoi, UserE user, CommentE comment) {
        this.noiDung = noiDung;
        this.ngayTraLoi = ngayTraLoi;
        this.user = user;
        this.comment = comment;
    }
}
