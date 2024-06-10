package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class PaymentDTO {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VNPayResponse {
        public String code;
        public String message;
        public String paymentUrl;
    }
}
