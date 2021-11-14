package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author derovi
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/secret").hasRole("ADMIN")
                .and()
            .formLogin()
                .permitAll()
                .and()
            .logout()
                .permitAll()
            .disable().cors();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User
            .withDefaultPasswordEncoder()
            .username("roman")
            .roles("ADMIN").password("pudge")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
