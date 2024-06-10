package poly.java5divineshop.Divineshop.Data.Model;

import lombok.*;
import poly.java5divineshop.Divineshop.Data.Dto.CommentDTO;
import poly.java5divineshop.Divineshop.Data.Dto.ReplyDTO;
import poly.java5divineshop.Divineshop.Data.Entity.CommentE;
import poly.java5divineshop.Divineshop.Data.Entity.ReplyE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyM {
    private Integer sysIdReply;
    private String noiDung;
    private Date ngayTraLoi = new Date();
    private UserE user;
    private CommentE comment;

    public static ReplyDTO convertReplyEToReplyDTO(ReplyE replyE) {
        return ReplyDTO.builder()
                .sysIdReply(replyE.getSysIdReply())
                .noiDung(replyE.getNoiDung())
                .ngayTraLoi(replyE.getNgayTraLoi())
                .user(replyE.getUser())
                .comment(replyE.getComment())
                .build();
    }

    public static ReplyE convertReplyDTOToReplyE(ReplyDTO replyDTO) {
        return ReplyE.builder()
                .sysIdReply(replyDTO.getSysIdReply())
                .noiDung(replyDTO.getNoiDung())
                .ngayTraLoi(replyDTO.getNgayTraLoi())
                .user(replyDTO.getUser())
                .comment(replyDTO.getComment())
                .build();
    }
}