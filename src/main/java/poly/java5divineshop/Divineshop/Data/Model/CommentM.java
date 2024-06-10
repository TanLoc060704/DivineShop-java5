package poly.java5divineshop.Divineshop.Data.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import poly.java5divineshop.Divineshop.Data.Dto.CommentDTO;
import poly.java5divineshop.Divineshop.Data.Entity.CommentE;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Entity.ReplyE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentM {
    private int sysIdComment;
    private String noiDung;
    private Date ngayBinhLuan = new Date();
    private UserE user;
    private ProductE product;
    private List<ReplyE> replies;

    public static CommentDTO convertCommentEToCommentDTO(CommentE commentE) {
        return CommentDTO.builder()
                .sysIdComment(commentE.getSysIdComment())
                .noiDung(commentE.getNoiDung())
                .ngayBinhLuan(commentE.getNgayBinhLuan())
                .user(commentE.getUser())
                .product(commentE.getProduct())
                .replies(commentE.getReplies())
                .build();
    }

    public static CommentE convertCommentDTOToCommentE(CommentDTO commentDTO) {
        return CommentE.builder()
                .sysIdComment(commentDTO.getSysIdComment())
                .noiDung(commentDTO.getNoiDung())
                .ngayBinhLuan(commentDTO.getNgayBinhLuan())
                .user(commentDTO.getUser())
                .product(commentDTO.getProduct())
                .replies(commentDTO.getReplies())
                .build();
    }

}
