package poly.java5divineshop.ConfigSecurity.Security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import poly.java5divineshop.ConfigSecurity.Security.CustomUserDetails;
import poly.java5divineshop.ConfigSecurity.Security.CustomUserDetailsService;
import poly.java5divineshop.ConfigSecurity.Security.SecurityConfig;
import poly.java5divineshop.ConfigSecurity.Security.authentication.CustomUserDetailsAuthenticationToken;
import poly.java5divineshop.ConfigSecurity.Security.service.TokenBlacklistService;
import poly.java5divineshop.Divineshop.exception.CustomException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService service;
    private final List<String> nonAuthenticatedUrls = List.of(
            SecurityConfig.nonAuthenticatedUrls
    );
    private final TokenBlacklistService tokenBlacklistService;
//    private static final Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (isNonAuthenticatedUrl(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        String token = getJwtToken(request).orElseThrow(() -> new CustomException("Authorization token không hơp lệ", HttpStatus.FORBIDDEN));
        DecodedJWT decodedJWT = jwtUtil.verifyToken(token);
        if (decodedJWT == null) {
            throw new CustomException("Token không xác thực", HttpStatus.FORBIDDEN);
        }
        CustomUserDetails customUserDetails = service.jwtToCustomUserDetails(decodedJWT);
        if (customUserDetails.getAccountE() == null) {
            tokenBlacklistService.blacklistToken(token);
            chain.doFilter(request, response);
            return;
        }
        var authenticationToken = new CustomUserDetailsAuthenticationToken(customUserDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private boolean isNonAuthenticatedUrl(String requestURI) {
        return nonAuthenticatedUrls.stream()
                .anyMatch(urlPattern -> requestURI.matches(convertToRegex(urlPattern)));
    }

    private String convertToRegex(String urlPattern) {
        return urlPattern.replace("**", ".*").replace("*", "[^/]+");
    }

    private Optional<String> getJwtToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return Optional.of(token.substring(7));
        }
        return Optional.empty();
    }
}
