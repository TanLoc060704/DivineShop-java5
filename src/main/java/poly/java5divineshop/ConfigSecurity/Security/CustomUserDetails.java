package poly.java5divineshop.ConfigSecurity.Security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;

import java.util.Collection;
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private AccountE accountE;
    private Collection<? extends GrantedAuthority> getAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorities;
    }

    @Override
    public String getPassword() {
        return accountE.getHashPassword();
    }

    @Override
    public String getUsername() {
        return accountE.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return accountE.isEnabled();
    }
}
