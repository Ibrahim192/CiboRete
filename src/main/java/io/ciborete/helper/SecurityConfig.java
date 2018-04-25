package io.ciborete.helper;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

//@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig  {
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//@formatter:off
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
// @formatter:on
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//@formatter:off
        http
                // disable CSRF for this application
                .formLogin() // Using form based login instead of Basic Authentication
                .loginProcessingUrl("/fanUser")
                .successForwardUrl("/fanUser")// Endpoint which will process the authentication request. This is where we will post our credentials to authenticate
                .and()
                .logout()
                .logoutUrl("/auth/logout") // Configures the URL to logout from application
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll() // Enabling URL to be accessed by all users (even un-authenticated)
                .antMatchers("/secure/admin").access("hasRole('ADMIN')") // Configures specified URL to be accessed with user having role as ADMIN
                .anyRequest().authenticated() // Any resources not mentioned above needs to be authenticated
                .and()
                .anonymous().disable(); // Disables anonymous authentication with anonymous role.
// @formatter:on
    }*/
}