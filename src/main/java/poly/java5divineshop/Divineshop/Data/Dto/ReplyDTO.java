package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.*;
import poly.java5divineshop.Divineshop.Data.Entity.CommentE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Integer sysIdReply;
    private String noiDung;
    private LocalDate ngayTraLoi = LocalDate.now();
    private UserE user;
    private CommentE comment;
}
