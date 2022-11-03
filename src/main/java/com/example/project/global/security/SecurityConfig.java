package com.example.project.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.project.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .cors().and()
                .csrf().disable()
                .exceptionHandling()

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()

                //user
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/users/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/users").authenticated()
                .antMatchers(HttpMethod.GET, "/users/{user-id}").authenticated()

                .antMatchers(HttpMethod.POST, "/companys").authenticated()
                .antMatchers(HttpMethod.GET, "/companys/invite").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/companys/invite").hasAuthority("USER")

                .antMatchers(HttpMethod.GET, "/admin/work/status/list").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/work/info/{user-id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/admin/user/{user-id}").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET, "/employees/work/info").authenticated()
                .antMatchers(HttpMethod.POST, "/employees/work/plan").authenticated()
                .antMatchers(HttpMethod.POST, "/employees/work/go").authenticated()
                .antMatchers(HttpMethod.POST, "/employees/leave").authenticated()
                .antMatchers(HttpMethod.GET, "/employees/wokr/status/list").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/employees/work/info/{user-id}").hasAuthority("USER")

                .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(jwtTokenProvider, objectMapper))
                .and().build();

    }
}