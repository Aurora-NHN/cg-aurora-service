package com.codegym.aurora.configuration;


import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.security.JwtAuthEntryPoint;
import com.codegym.aurora.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.Filter;


@EnableAutoConfiguration
@EnableWebSecurity
@RequiredArgsConstructor
@ComponentScan(basePackageClasses = {
        UserDetailsService.class,
        JwtAuthEntryPoint.class,
        UserRepository.class
})
@Component
@Configuration
public class SecurityConfiguration  {

    private final UserDetailsService userDetailsService;

    private final JwtAuthEntryPoint unauthorizedHandler;


    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public Filter jwtAuthenticationFilter() {
        return new JwtAuthFilter();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable();

        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                // configure not use session to save client info
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeHttpRequests()
                .antMatchers( "/api/login", "/api/logout","/api/register-user",
                        "/api/vnpay/payment-success", "/api/numerology",
                        "/api/products/**", "/api/categories",
                        "/api/blogs/**","/api/google-login")
                .permitAll();

        httpSecurity.authorizeHttpRequests()
                .antMatchers("/api/vnpay/set-active-status")
                .hasRole("ADMIN");

        httpSecurity.authorizeHttpRequests()
                .antMatchers("/api/users/**","/api/vnpay/**",
                        "/api/order/**", "/api/cart/**",
                        "/api/numerology/**")
                .hasRole("USER");

        httpSecurity.authorizeHttpRequests()
                .antMatchers("/api/admin/login")
                .permitAll()
                .antMatchers("/api/**")
                .hasRole("ADMIN");

        // Configure remember me (save token in database)
        httpSecurity.authorizeHttpRequests()
                .and().rememberMe()
                .tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60);//24 hours

        // Use JwtAuthorizationFilter to check token -> get user info
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }


    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }


}
