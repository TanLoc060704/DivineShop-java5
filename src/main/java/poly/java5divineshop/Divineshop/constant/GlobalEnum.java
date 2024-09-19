package poly.java5divineshop.Divineshop.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalEnum {
    ROLE_ADMIN("Admin"),
    ROLE_THUKY("Thư ký"),
    ROLE_THUQUY("Thủ quỹ"),
    ROLE_DOANTRUONG("Đoàn trưởng"),
    ROLE_DOANTRUONG_OANHVUNAM("Đoàn trưởng Oanh Vũ Nam"),
    ROLE_DOANTRUONG_OANHVUNU("Đoàn trưởng Oanh Vũ Nữ"),
    ROLE_DOANTRUONG_THIEUNAM("Đoàn trưởng Thiếu Nam"),
    ROLE_DOANTRUONG_THIEUNU("Đoàn trưởng Thiếu Nữ"),
    ROLE_DOANTRUONG_NGANHTHANH("Đoàn trưởng Ngành Thanh");
    private final String description;
}
