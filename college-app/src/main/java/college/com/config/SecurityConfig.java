package college.com.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
            	    .loginPage("/login")
            	    .failureUrl("/login?error=true") // 🔥 ADD THIS
            	    .successHandler((request, response, authentication) -> {

            	        String role = authentication.getAuthorities().iterator().next().getAuthority();

            	        if (role.equals("ROLE_ADMIN")) {
            	            response.sendRedirect("/admin");
            	        } else {
            	            response.sendRedirect("/tracker");
            	        }

            	    })
            	    .permitAll()
            	)

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}