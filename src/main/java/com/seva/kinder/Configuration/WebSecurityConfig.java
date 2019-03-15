package com.seva.kinder.Configuration;

import com.seva.kinder.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/images/**",
            "/js/**",
            "/js/**",
            "/fonts/**",
            "/signin",
            "/"
    };


    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userSecurityService = myUserService();
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService myUserService() {
        return new UserSecurityService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
//                .antMatchers("/admin/**").hasAuthority("System-admin")
                .antMatchers("/signinsuccess").hasAuthority("admin")
                .antMatchers("/signinsuccess").hasAuthority("user")
//                .antMatchers("/admin/**").hasAuthority("Client-user")
//                .antMatchers("/employee/**").hasAuthority("USER")
                .anyRequest()
                .authenticated()
                .and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/signin")
                .loginProcessingUrl("/signin")
                .failureUrl("/signin?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/signin").and().exceptionHandling();
    }
}
