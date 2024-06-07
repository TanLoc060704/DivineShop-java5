package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.*;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private int userId;
    private String username;
    private String email;
    private String hashedPassword;
    private boolean isEnabled;

    public static AccountE convertAccountDTOToAccountE(AccountDTO accountDTO) {
        return AccountE.builder()
                .id(accountDTO.getUserId())
                .username(accountDTO.getUsername())
                .email(accountDTO.getEmail())
                .hashPassword(accountDTO.getHashedPassword())
                .isEnabled(accountDTO.isEnabled())
                .build();
    }
}
