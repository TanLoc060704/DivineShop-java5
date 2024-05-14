package poly.java5divineshop.Divineshop.Data.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.java5divineshop.Divineshop.Data.Dto.AccountDTO;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountM {

    private int userId;
    private String username;
    private String email;
    private String hashPassword;
    private boolean isEnabled;

    public static AccountM convertAccountEToAccountM(AccountE accountE) {
        return AccountM.builder()
                .userId(accountE.getId())
                .username(accountE.getUsername())
                .email(accountE.getEmail())
                .hashPassword(accountE.getHashPassword())
                .isEnabled(accountE.isEnabled())
                .build();
    }
    public static List<AccountM> convertListAccountEToListAccountM(List<AccountE> accountEList) {
        return  accountEList.stream()
                .map(e -> convertAccountEToAccountM(e))
                .collect(Collectors.toList());
    }

}
