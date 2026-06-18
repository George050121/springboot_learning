package com.example.demo.config;

import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. 注入刚才写的翻译官
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 2. 定义密码编码器（非常重要！数据库里的密码必须是加密过的）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3. 配置拦截规则
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            // 1. 只保留登录页和静态资源的放行
            // 注意：如果你还没写自定义的 /login 页面，这里建议暂时不要加 "/login"，
            // 而是直接删掉 .loginPage("/login") 这一行，使用默认登录页。
                .requestMatchers("/css/**", "/js/**").permitAll()
                .anyRequest().authenticated() // 其他所有请求都需要登录
            )
            .formLogin(form -> form
                // .loginPage("/login") // 自定义登录页路径（如果你还没写 Controller，先删掉这行，用默认的）
                .permitAll()
            )
            .userDetailsService(userDetailsService); // 【关键】把翻译官注册进去

        return http.build();
    }
}
