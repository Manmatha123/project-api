package com.ecommerce.ecommerce.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ecommerce.ecommerce.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/api/user/saveorupdate","/v1/api/user/signin").permitAll()
                       
                        .requestMatchers("/v1/api/user/profile","/v1/api/user/list",
                        "/v1/api/store/id/{id}","/v1/api/store/saveorupdate","/v1/api/store/user/id/{id}","/v1/api/product/bill"
                        ,"/v1/api/product/saveorupdate","/v1/api/product/delete/id/{id}","/v1/api/product/list","/v1/api/product/id/{id}",
                        "/v1/api/order/saveorupdate","/v1/api/user/info","/v1/api/user/update","/v1/api/store/info","/v1/api/product/list/name/{name}"
                        ).hasRole("ADMIN")
                        
                        .requestMatchers("/v1/api/store/category/list",
                        "/v1/api/store/list/place/{place}","/v1/api/user/info",
                        "/v1/api/store/list/type/{type}","/v1/api/user/update"
                        
                       
                        ).hasRole("USER")

                        .anyRequest().authenticated())
                        .csrf(AbstractHttpConfigurer::disable);
                        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration  configuration)throws Exception{
    return configuration.getAuthenticationManager();
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}