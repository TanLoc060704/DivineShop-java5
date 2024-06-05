package poly.java5divineshop.ConfigSecurity.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    /**
     * <h1>
     * muốn Call api trong postman thì phải comment method SecurityFilterChain
     * </h1>
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/").permitAll()
                .requestMatchers("/cart").authenticated()
                .requestMatchers("/userinfo").hasRole("EMPLOYEE")
                .requestMatchers("/leaders").hasRole("MANAGER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form ->
                form
                    .loginPage("/log-in")
                    .loginProcessingUrl("/authenticateTheUser")
                    .usernameParameter("username")
                    .passwordParameter("password")
//                    .defaultSuccessUrl("/",true)
                    .successHandler(authenticationSuccessHandler())
                    .permitAll()
            )
            .logout(logout -> logout.permitAll()
            )
            .exceptionHandling(configurer ->
                    configurer.accessDeniedPage("/access-denied")
            );

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
        return builder.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                Cookie loginSuccessCookie = new Cookie("loginSuccess", "true");
                response.addCookie(loginSuccessCookie);
                response.sendRedirect("/");
            }
        };
    }
/*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mery = User.builder()
                .username("mery")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mery, susan);
        }
    }
*/
    // add support for JDBC ... no more hardcoded users :=>
/*
    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "select username,hash_password,is_enabled from account where username = ?"
        );
        //define query to rerieve the authrities / rolles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "select username,role from roles where username = ?"
        );
        return jdbcUserDetailsManager;
    }
*/

}
