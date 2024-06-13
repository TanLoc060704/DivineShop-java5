package poly.java5divineshop.Divineshop.Data.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import poly.java5divineshop.Divineshop.Data.Entity.OderE;
import poly.java5divineshop.Divineshop.Data.Entity.PaymentE;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentM {
    private Integer id;
    private Date thoigian;
    private String mota;
    private Float sotien;
    private Boolean trangthai;
    private String tenuser;

    public static PaymentM convertPaymentEToPaymentM(PaymentE paymentE) {
        return PaymentM.builder()
                .id(paymentE.getId())
                .thoigian(paymentE.getThoigian())
                .mota(paymentE.getMota())
                .sotien(paymentE.getSotien())
                .trangthai(paymentE.getTrangthai())
                .tenuser(paymentE.getTenuser())
                .build();
    }

    public static List<PaymentM> convertListPaymentEToListPaymentM(List<PaymentE> listPaymemtE) {
        return listPaymemtE.stream()
                .map(e -> convertPaymentEToPaymentM(e))
                .collect(Collectors.toList());
    }
}
