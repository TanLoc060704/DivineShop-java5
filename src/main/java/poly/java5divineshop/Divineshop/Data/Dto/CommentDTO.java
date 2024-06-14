package poly.java5divineshop.Divineshop.Data.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
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
public class CommentDTO {
    private int sysIdComment;
    private String noiDung;
    private Date ngayBinhLuan = new Date();
    private UserE user;
    private ProductE product;
    private List<ReplyE> replies;
}

