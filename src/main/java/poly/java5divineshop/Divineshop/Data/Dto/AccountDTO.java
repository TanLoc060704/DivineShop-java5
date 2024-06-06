package poly.java5divineshop.Divineshop.Data.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String username;
    private String email;
    private String hashedPassword;
    private boolean isEnabled;

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static AccountE convertAccountDTOToAccountE(AccountDTO accountDTO) {
        return AccountE.builder()
                .username(accountDTO.getUsername())
                .email(accountDTO.getEmail())
                .hashPassword(accountDTO.getHashedPassword())
                .isEnabled(accountDTO.isEnabled())
                .build();
    }
}
