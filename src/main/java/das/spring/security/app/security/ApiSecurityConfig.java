package das.spring.security.app.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {

    @Value("${user.supervisor.name}")
    private String supervisorUserName;

    @Value("${user.admin.name}")
    private String adminUserName;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails supervisorUserDetails = User.withUsername(supervisorUserName)
                .password(passwordEncoder().encode("pass"))         // passing raw String does not work
                .roles("SUPERVISOR")
                .build();

        UserDetails adminUserDetails = User.withUsername(adminUserName)
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(supervisorUserDetails,adminUserDetails);
//        return new CustomUserDetails();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/actuator", "/actuator/**","/api/admin")
                .hasRole("ADMIN")
                .requestMatchers("/api/sup")
                .hasRole("SUPERVISOR")
                .requestMatchers("/api/public","/public/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return httpSecurity.build();
    }
}
