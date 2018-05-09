package com.zuzhi.corespring.config;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

/**
 * SecurityConfig
 *
 * @author zuzhi
 * @date 15/03/2018
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ALL = "*";

    private static final List<String> WILDCARD_PERMIT_ALL =
            Collections.unmodifiableList(Collections.singletonList(ALL));

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}pass")
                .authorities("ROLE_ADMIN");
        /// You can .roles() for the same purpose
        // .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.httpBasic()
                .and()
                .cors()
                .and()
                .authorizeRequests()
                // actuator
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                // books
                .antMatchers(HttpMethod.GET, "/api/v1/books").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/books/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/v1/books/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/books/*").hasRole("ADMIN")
                // ratings
                .regexMatchers("^/ratings\\?bookId.*$").authenticated()
                .antMatchers(HttpMethod.POST, "/ratings").authenticated()
                .antMatchers(HttpMethod.PATCH, "/ratings/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/ratings/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/ratings").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
        // @formatter:on
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(WILDCARD_PERMIT_ALL);
        configuration.setAllowedMethods(WILDCARD_PERMIT_ALL);
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}