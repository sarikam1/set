package com.example.bullshitsetbackend.config;

import com.example.bullshitsetbackend.repository.PlayerRepo;
import com.example.bullshitsetbackend.security.AuthenticationFilter;
import com.example.bullshitsetbackend.security.UserDetailsServiceImpl;
import com.example.bullshitsetbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.cert.Extension;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by pdybka on 15.06.16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final static Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class.getName());


//    private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
//            new AntPathRequestMatcher("/api/**")
//    );

//    @Value("{$serviceUrl}")
//    private String serviceUrl;

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("api/users/user").permitAll()
                    .anyRequest().authenticated().and().
                        httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}













//
//    @Autowired
//    public SecurityConfig(final AuthenticationProvider authenticationProvider) {
//        super();
//        this.provider = authenticationProvider;
//    }
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new UserDetailsServiceImpl(playerRepository)).passwordEncoder(new BCryptPasswordEncoder());
//
//    }
//
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(provider);
//    }
//
//    @Override
//    public void configure(final WebSecurity webSecurity) {
//        webSecurity.ignoring().antMatchers("/token/**");
//    }
//
//    @Bean
//    AuthenticationFilter authenticationFilter() throws Exception {
//        final AuthenticationFilter filter = new AuthenticationFilter(PROTECTED_URLS);
//        filter.setAuthenticationManager(authenticationManager());
//        //filter.setAuthenticationSuccessHandler(successHandler());
//        return filter;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        LOGGER.setLevel(Level.INFO);
//        http = http.cors().and().csrf().disable();
//        http = http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and();
//        http = http
//                .exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
//                            response.sendError(
//                                    HttpServletResponse.SC_UNAUTHORIZED,
//                                    ex.getMessage()
//                            );
//                        }
//                )
//                .and();
//
//        http.authorizeRequests()
//                // Our public endpoints
//                .antMatchers("/public/**").permitAll()
//                // Our private endpoints
//                .anyRequest().authenticated();
//        http.addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class);
//
//        http.formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password");
////                .successHandler(new AuthenticationSuccessHandler() {
////                    @Override
////                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
////                        //https://www.codejava.net/frameworks/spring-boot/spring-security-authentication-success-handler-examples
////                        String username = authentication.getName();
////                        String password = (String) authentication.getCredentials();
////                        String token = playerService.login(username, password);
////                        LOGGER.info("Returning token " + token + " for user " + username);
////                    }
////                })
////                .defaultSuccessUrl("http://localhost:4200/", true).
////                and().
////                    authenticationProvider(provider).addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class).
////                        authorizeRequests().requestMatchers(PROTECTED_URLS).authenticated()
//    }
//}