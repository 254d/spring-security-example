package com.example.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    String idForEncode = "bcrypt";
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put(idForEncode, new BCryptPasswordEncoder());
    return new DelegatingPasswordEncoder(idForEncode, encoders);
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
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated())
        // h2-consoleの表示不具合抑止
        .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
        .headers(headers -> headers.frameOptions().disable());

    // System.out.println("password : " + passwordEncoder().encode("admin1234"));

    return http.build();
  }
}
