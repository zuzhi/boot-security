package com.zuzhi.corespring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SecurityConfig
 *
 * @author zuzhi
 * @date 15/03/2018
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
}