package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
