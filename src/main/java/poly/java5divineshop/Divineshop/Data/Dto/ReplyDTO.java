package poly.java5divineshop.Divineshop.Data.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import poly.java5divineshop.Divineshop.Data.Entity.CommentE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Integer sysIdReply;
    private String noiDung;
    private Date ngayTraLoi = new Date();
    private UserE user;
    private CommentE comment;
}
