package poly.java5divineshop.Divineshop.Data.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class CommentE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sys_id_comment")
    private Integer sysIdComment;

    @Column(name = "noi_dung", columnDefinition = "nvarchar(255)", nullable = false)
    private String noiDung;

    @Column(name = "ngay_binh_luan", nullable = false)
    private LocalDate ngayBinhLuan = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "Sys_id_user", nullable = false)
    private UserE user;

    @ManyToOne
    @JoinColumn(name = "Sys_id_product", nullable = false)
    private ProductE product;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReplyE> replies = new ArrayList<>();

    public void addReply(ReplyE replyE) {
        replies.add(replyE);
        replyE.setComment(this); // Set the back-reference in the ReplyE entity
    }
}
