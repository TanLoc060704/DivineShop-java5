package poly.java5divineshop.Divineshop.Data.Model;

import lombok.*;
import poly.java5divineshop.Divineshop.Data.Entity.PaymentE;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentM {
    private Integer id;
    private LocalDate thoigian;
    private String mota;
    private Float sotien;
    private Boolean trangthai;
    private String tenUser;

    public static PaymentM convertPaymentEToPaymentM(PaymentE paymentE) {
        return PaymentM.builder()
                .id(paymentE.getId())
                .thoigian(paymentE.getThoiGian())
                .mota(paymentE.getMota())
                .sotien(paymentE.getSoTien())
                .trangthai(paymentE.getTrangThai())
                .tenUser(paymentE.getTenUser())
                .build();
    }

    public static List<PaymentM> convertListPaymentEToListPaymentM(List<PaymentE> listPaymemtE) {
        return listPaymemtE.stream()
                .map(e -> convertPaymentEToPaymentM(e))
                .collect(Collectors.toList());
    }
}
