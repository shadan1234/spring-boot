//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/public/**").permitAll() // Allow public endpoints
//                        .anyRequest().authenticated() // Secure all other endpoints
//                )
//                .httpBasic(); // Use Basic Auth (or form login, JWT, etc.)
//        return http.build();
//    }
//}
