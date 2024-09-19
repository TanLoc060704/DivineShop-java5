package poly.java5divineshop.ConfigSecurity.Security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import poly.java5divineshop.ConfigSecurity.Security.CustomUserDetails;
import poly.java5divineshop.ConfigSecurity.Security.data.LoginReponseDTO;
import poly.java5divineshop.ConfigSecurity.Security.data.LoginRequestDTO;
import poly.java5divineshop.ConfigSecurity.Security.jwt.JwtUtil;
import poly.java5divineshop.Divineshop.exception.CustomException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
//    private final CustomLoginAttemptService loginAttemptService;
    private final JwtUtil jwtUtil;

    public LoginReponseDTO authenticate(LoginRequestDTO dto, String sessionId) {
//        if (loginAttemptService.isBlocked(dto.getUsername())) {
//            throw new CustomException("Tài khoản đang bị khóa do nhiều lần đăng nhập không thành công. Vui lòng thử lại trong vòng 30 phút!", HttpStatus.LOCKED);
//        }
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var userDetails = (CustomUserDetails) authentication.getPrincipal();
//            loginAttemptService.resetFailedAttempts(dto.getUsername());
            return LoginReponseDTO.builder()
                    .token(jwtUtil.generateToken(userDetails, sessionId))
                    .build();
        } catch (BadCredentialsException e) {
//            loginAttemptService.loginFailed(dto.getUsername());
            throw new CustomException("Sai thông tin đăng nhập", HttpStatus.UNAUTHORIZED);
        }
    }

}
