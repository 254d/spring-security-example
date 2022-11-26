package com.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/", true)
        .failureUrl("/login-error")
        .permitAll())
        .logout(logout -> logout
            .logoutSuccessUrl("/"))
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .mvcMatchers("/admin/**").hasRole("ROLE_ADMIN")
            .anyRequest().authenticated())
        // h2-consoleの表示不具合抑止
        .csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"))
        .headers(headers -> headers.frameOptions().disable());

    // System.out.println("password : " + passwordEncoder().encode("admin1234"));

    return http.build();
  }
}
