package poly.java5divineshop.Divineshop.Data.Dto;


import lombok.*;
import poly.java5divineshop.Divineshop.Data.Entity.ProductE;
import poly.java5divineshop.Divineshop.Data.Entity.ReplyE;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private int sysIdComment;
    private String noiDung;
    private LocalDate ngayBinhLuan = LocalDate.now();
    private UserE user;
    private ProductE product;
    private List<ReplyE> replies;
}

