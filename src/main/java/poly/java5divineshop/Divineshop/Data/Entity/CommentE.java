package poly.java5divineshop.Divineshop.Data.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "[Comment]")
public class CommentE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_comment")
    private int sysIdComment;

    @Column(name = "noi_dung", columnDefinition = "nvarchar(255)", nullable = false)
    private String noiDung;


    @Column(name = "ngay_binh_luan", nullable = false)
    private Date ngayBinhLuan = new Date();

    @ManyToOne
    @JoinColumn(name = "Sys_id_user", nullable = false)
    private UserE user;

    @ManyToOne
    @JoinColumn(name = "Sys_id_product", nullable = false)
    private ProductE product;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<ReplyE> replies;

    public void addReplyList(ReplyE replyE) {
        if (replies == null) {
            replies = new ArrayList<>();
        }
        replies.add(replyE);
    }
}
