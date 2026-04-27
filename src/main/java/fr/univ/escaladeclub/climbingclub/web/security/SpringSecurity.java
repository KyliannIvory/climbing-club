package fr.univ.escaladeclub.climbingclub.web.security;

import fr.univ.escaladeclub.climbingclub.repository.MemberRepository;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringSecurity {

    @Autowired
    private MemberRepository memberRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] anonymousRequests = { "/", "/login","/home", "/register",
                "/forgot-password","/reset-password",
                "/categories","/categories/*","/outings/*",
                "/category-details","/outing-details",
                "/search",
                 "/css/**", "/images/**", "/js/**", "/fonts/**" };

        http.authorizeHttpRequests(config -> {
            config.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll();
            config.requestMatchers(anonymousRequests).permitAll();
            config.anyRequest().authenticated();
        });

        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
        );


        http.logout(logout -> logout
                .logoutUrl("/logout")                        // URL appelée
                .logoutSuccessUrl("/login?logout")           // Redirection après déconnexion
                .permitAll()
        );

        http.csrf(config ->
                config.ignoringRequestMatchers(anonymousRequests)
        );
        return http.build();
    }

    @Bean
    public AuthenticationProvider myAuthenticationProvider(
            @Autowired PasswordEncoder encoder,
            @Autowired UserDetailsService userDetailsService) {

        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
