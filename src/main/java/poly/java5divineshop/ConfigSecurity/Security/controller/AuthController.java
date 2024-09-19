package poly.java5divineshop.ConfigSecurity.Security.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import poly.java5divineshop.ConfigSecurity.Security.data.LoginRequestDTO;
import poly.java5divineshop.ConfigSecurity.Security.jwt.JwtUtil;
import poly.java5divineshop.ConfigSecurity.Security.service.AuthService;
import poly.java5divineshop.ConfigSecurity.Security.service.TokenBlacklistService;
import poly.java5divineshop.Divineshop.constant.ResponseObject;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final HttpServletRequest httpServletRequest;
    private final TokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public ResponseObject<?> doLogin(@RequestBody @Valid LoginRequestDTO dto) {
        var session = httpServletRequest.getSession();
        return ResponseObject.builder()
                .status(HttpStatus.OK)
                .data(authService.authenticate(dto, session.getId()))
                .message("Đăng nhập thành công")
                .build();
    }

    @GetMapping("/is-login")
    public ResponseObject<?> isLogin() {
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        return ResponseObject.builder()
                .status(HttpStatus.OK)
                .message("Hello " + jwtUtil.extractUsername(token) + "!")
                .build();
    }

    @PostMapping("/logout")
    public ResponseObject<?> logout() {
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        tokenBlacklistService.blacklistToken(token);
        return ResponseObject.builder()
                .status(HttpStatus.OK)
                .data(tokenBlacklistService.isTokenBlacklisted(token))
                .message("Logout successfully")
                .build();
    }
}

