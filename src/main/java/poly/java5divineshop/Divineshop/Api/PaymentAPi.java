package poly.java5divineshop.Divineshop.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.java5divineshop.Divineshop.Data.Dto.PaymentDTO;
import poly.java5divineshop.Divineshop.Data.Entity.PaymentE;
import poly.java5divineshop.Divineshop.Service.ServiceImpl.Paymentservice;
import poly.java5divineshop.Divineshop.Service.UserService;
import poly.java5divineshop.Divineshop.Utils.ResponseObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${spring.application.api-prefix}/payment")
@RequiredArgsConstructor
public class PaymentAPi {

    @Autowired
    Paymentservice paymentservice;

    @Autowired
    UserService userService;

    @GetMapping("/vn-pay")
    public String pay(HttpServletRequest request, @RequestParam String name) {
        String url = paymentservice.createVnPayPayment(request, name).paymentUrl;
        try{
            URL urlquery = new URL(url);
            String query = urlquery.getQuery();
            Map<String, String> parameters = getQueryParameters(query);

            String vnpAmount = parameters.get("vnp_Amount"); //số tiền người thành toán
            String vnp_TxnRef = parameters.get("vnp_TxnRef"); //mã hóa đơn
            String vnp_OrderInfo = parameters.get("vnp_OrderInfo"); //người lập hóa đơn
            int viTriDauGach = vnp_OrderInfo.indexOf("-"); //người lập hóa đơn

            String vnp_CreateDate = parameters.get("vnp_CreateDate").substring(0,8); // ngày lập hóa đơn
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate date = LocalDate.parse(vnp_CreateDate, formatter);

            PaymentE payment = new PaymentE();
            payment.setMota(vnp_TxnRef);
            payment.setThoigian(Date.valueOf(date));
            payment.setSotien(Float.valueOf(vnpAmount)/100);
            payment.setTenuser(vnp_OrderInfo.substring(viTriDauGach + 1));
            payment.setTrangthai(false);

            paymentservice.save(payment);
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static Map<String, String> getQueryParameters(String query) {
        Map<String, String> parameters = new HashMap<>();
        if (query == null || query.isEmpty()) {
            return parameters;
        }

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            try {
                String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
                parameters.put(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return parameters;
    }

    @GetMapping("/vn-pay-callback")
    public String payCallbackHandler(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes) throws IOException, SQLException {
        String status = request.getParameter("vnp_ResponseCode");

        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo"); //người lập hóa đơn
        int viTriDauGach = vnp_OrderInfo.indexOf("-"); //người lập hóa đơn
        String name = vnp_OrderInfo.substring(viTriDauGach + 1);
        String tien = request.getParameter("vnp_Amount"); // tiền
        Double tienvalue = Double.valueOf(tien)/100;

        String maDonHang = request.getParameter("vnp_TxnRef");

        if (status.equals("00")) {
            paymentservice.updatepaymentbyuser(maDonHang);

            Double tienhienco = Double.valueOf(userService.getUserByTenDangNhap(name).getSoDu());
            tienhienco += tienvalue;

            userService.updateUserByTenDangNhap(tienhienco+"",name);
            // Thay đổi giá trị của flag thành true
            session.setAttribute("flag", true);
            redirectAttributes.addAttribute("flag", false);
            response.sendRedirect("/add-funds");
            return "true";
        } else {
            session.setAttribute("flag1", true);
            response.sendRedirect("/add-funds");
            return "false";
        }
    }

}
