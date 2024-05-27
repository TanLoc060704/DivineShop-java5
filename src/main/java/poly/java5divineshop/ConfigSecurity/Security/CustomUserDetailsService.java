package poly.java5divineshop.ConfigSecurity.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import poly.java5divineshop.ConfigSecurity.Service.AccountService;
import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
import poly.java5divineshop.Divineshop.Data.Entity.RoleE;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountService accountService ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountE accountE = accountService.findByUsernameSecurity(username);
        if(accountE == null){
            return null;
        }
        Collection<GrantedAuthority> getAuthoritySet = new HashSet<>();
        List<RoleE> roleS = accountE.getRoles();
        for(RoleE role : roleS){
            getAuthoritySet.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new CustomUserDetails(accountE,getAuthoritySet );
    }
}
