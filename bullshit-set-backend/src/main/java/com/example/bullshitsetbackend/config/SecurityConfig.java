package com.example.bullshitsetbackend.config;

import com.example.bullshitsetbackend.repository.PlayerRepo;
import com.example.bullshitsetbackend.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.cert.Extension;


/**
 * Created by pdybka on 15.06.16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private PlayerRepo playerRepository;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
           auth.userDetailsService(new UserDetailsServiceImpl(playerRepository)).passwordEncoder(new BCryptPasswordEncoder());

//        auth.inMemoryAuthentication()
//                .withUser("ok_name").password(passwordEncoder().encode("ok_password")).roles("USER");
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("http://localhost:4200/", true)
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}