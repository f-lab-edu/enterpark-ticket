package com.example.enterparkticket.apis.enduser.config

import com.example.enterparkticket.apis.enduser.config.jwt.JwtTokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtTokenFilter: JwtTokenFilter) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it.requestMatchers("/login").permitAll()
                it.requestMatchers("/v1/auth/oauth/**").permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}
