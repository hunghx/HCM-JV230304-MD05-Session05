package ra.hunghx.sercurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ra.hunghx.sercurity.jwt.JwtAuthTokenFilter;
import ra.hunghx.sercurity.principle.UserDetailsServiceCustom;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // caau hinh phan quyen tren phuong thuc
public class SecurityConfig { // cấu hình bảo mật
    @Autowired
    private JwtAuthTokenFilter jwtAuthTokenFilter;
    @Autowired
    private UserDetailsServiceCustom userDetailsServiceCustom;
    @Bean
    public PasswordEncoder passwordEncoder(){ // max hoa mat khau
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceCustom);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // phi trạng thai
                .authorizeHttpRequests( // phaan quyeenf theo duong dan
                        auth -> auth.requestMatchers("/api/v1/auth/**").permitAll() // ko caanf xac thuc
                                .requestMatchers("/api/v1/user/**").hasAuthority("ROLE_USER") // chir co User ms truy cam
                                .requestMatchers("/api/v1/admin/**").hasAuthority("ROLE_ADMIN") // chir co admin ms truy cam
                                .requestMatchers("/api/v1/manager/**").hasAuthority("ROLE_MANAGER") // chir co admin ms truy cam
                                .requestMatchers("/api/v1/adminormanager/**").hasAnyAuthority("ROLE_MANAGER","ROLE_ADMIN") // chir co admin ms truy cam
//                                .requestMatchers("/api/v1/user/**").hasRole("ROLE") // chir co User ms truy cam
                                .anyRequest().authenticated()
                );
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
