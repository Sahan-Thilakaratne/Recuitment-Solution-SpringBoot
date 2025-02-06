package com.recruitmentSolution.Recruitment_Solution;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration // This tells Spring that this class contains bean definitions
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //@Bean
    //public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
    //    http.csrf().disable().authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/register").permitAll().anyRequest().authenticated());

    //    return http.build();
    //}


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Allow all requests without authentication
                );

        return http.build();
    }

}
