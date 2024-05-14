//package poly.java5divineshop.ConfigSecurity.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import poly.java5divineshop.ConfigSecurity.Service.AccountService;
//import poly.java5divineshop.ConfigSecurity.Service.SerciveImpl.AccountImpl;
//import poly.java5divineshop.Divineshop.Data.Entity.AccountE;
//import poly.java5divineshop.Divineshop.Data.Entity.Role;
//import poly.java5divineshop.Divineshop.Data.Model.AccountM;
//import poly.java5divineshop.Divineshop.Repo.AccountRepo;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private AccountService accountService ;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AccountE accountE = accountService.findByUsername(username);
//        if(accountE == null){
//            return null;
//        }
//        Collection<GrantedAuthority> getAuthoritySet = new HashSet<>();
//        Set<Role> roles = accountE.getRoles();
//        for(Role role : roles){
//            getAuthoritySet.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//
//        return new CustomUserDetails(accountE,getAuthoritySet );
//    }
//}
